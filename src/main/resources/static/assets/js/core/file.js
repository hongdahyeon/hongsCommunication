class Upload {
    constructor(selector, maxCnt = 10, uploadTy = ['zip', 'text', 'pptx', 'xls', 'ppt', 'xlsx', 'jpg', 'hwp' ,'pdf', 'doc' ,'jpeg', 'png', 'gif', 'txt', 'csv', 'mp4', 'json']) {
        this._selector = selector
        this.upload_uuid = this.getUUID();
        this.maxUploadCnt = maxCnt
        this.uploading = false
        this.tempUploadCnt = 0
        this.uploadTy = uploadTy
        this._removes = []
        this._uid = null
        this._currentFiles = {}

        const { Dashboard, Tus } = Uppy
        this._uppy = new Uppy.Uppy({
            locale,
            autoProceed: true,
            onBeforeFileAdded: (file) => {

                const fileExtension = file.extension

                if(!this.uploading) {
                    if(this.tempUploadCnt === this.maxUploadCnt) {
                        Util.alert(`최대 업로드 가능한 파일 개수는 ${this.maxUploadCnt}개 입니다.`)
                        return false
                    }

                    if(!this.uploadTy.includes(fileExtension)) {
                        Util.alert("업로드 불가능한 확장자입니다.")
                        return false
                    }
                }

                if(this.uploading) {

                    if(this.tempUploadCnt === this.maxUploadCnt) Util.alert(`최대 업로드 가능한 파일 개수는 ${this.maxUploadCnt}개 입니다. <br> 확인부탁드립니다.`)
                    if(!this.uploadTy.includes(fileExtension)) Util.alert("업로드 불가능한 확장자가 있습니다. <br> 확인부탁드립니다.")

                }

                if(this._uid != null) {
                    file.meta = {
                        ...file.meta,
                        uid: this._uid
                    }
                }

                this.tempUploadCnt ++;
                return file
            }
        })

        this._uppy.use(Dashboard,{
            inline: true,
            target: this._selector,
            width: '100%',
            height: 400,
            showRemoveButtonAfterComplete: true
        })

        this._uppy.use(Tus, {
            endpoint: `/api/tus/files/upload?uploadId=${this.upload_uuid}`,
            onShouldRetry: this._onShouldRetry,
            onBeforeRequest: (req, file) => {
                const {header, token} = Http.getCookieInfo()
                req._xhr.setRequestHeader(header, token);
                this._currentFiles[req._url] = file
            },
            onAfterResponse: (req, res) => {
                return new Promise(resolve => {
                    this._currentFiles[req._url].meta.fileId = res.getHeader("X-File-Id")
                    resolve()
                })
            }
        })

        this._uppy.on('upload-success', (file, upload) => {
            file.meta.url = upload.uploadURL
            file.meta.saved = false
            if( !file.meta.saved ) {
                file.meta.name = `[임시] ${file.name}`
            }
        })

        this._uppy.on('upload-error', (file, responseObject) => {
            console.error("upload-error")
        })

        this._uppy.on('file-removed', (file, reason) => {
            this._removeFile(file, reason)
        })
    }

    getUUID() { // UUID v4 generator in JavaScript (RFC4122 compliant)
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
            let r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 3 | 8);
            return v.toString(16);
        });
    }

    _onShouldRetry(err, retryAttempt, options, next){
        const status = err?.originalResponse?.getStatus()
        if ( status === 405 ) {
            try {
                console.error("405 error happen")
            } catch (e) {
                window.location.href = '/login'
            } finally {
                return false
            }
        }
        return next(err)
    }

    _removeFile(file, reason) {
        const fileUrl = file.meta.url
        if(reason === 'removed-by-user') {
            this.tempUploadCnt --;
            if( !file.meta.saved ) {
                Http.delete(`/api/tus/files/temp/delete.json?fileUrl=${fileUrl}`)
            } else this._removes.push(fileUrl)
        }
    }

    setUid(fileUid) {
        this._uid = fileUid
    }

    getTempFiles(){
        const allFiles = this._uppy.getFiles();
        const tempFiles = allFiles.filter(x => !x.meta.saved).map(x => ({
            fileName: x.name,
            fileUrl: x.meta.url,
            fileId: x.meta.fileId,
            fileType: x.meta.type,
            fileSize: x.data.size,
            extension: x.extension
        }))

        return tempFiles
    }


    getRemoveFileUrls(){
        return this._removes
    }

    drawEditFile(fUid, upload){
        const file = {container: $('.file-container'), textEmpty: '첨부된 파일이 없습니다.'}
        if(fUid !== null) {
            upload.setUid(fUid);
            Http.get(`/api/hong/files/list.json?fileUid=${fUid}`).then((res) => {
                const list = res.message
                if (list.length !== 0) {
                    file.container.html('')
                    list.forEach(data => upload._addStaticFile(data))
                } else file.container.html(file.textEmpty)
            })
        } else {
            upload.setUid(null)
            file.container.html(file.textEmpty)
        }
    }

    _addStaticFile(file) {
        const options = {}
        this.uploading = true;

        let fileData;
        if( file.fileType.toString().includes('image') ) {
            options.preview = `/api/hong/files/download?fileUrl=${file.fileUrl}`
            fileData = new File([options.preview], file.fileName, {
                lastModified: new Date(file.regDt).getTime(),
                type: file.fileType
            });

        } else fileData =  { size: file.fileSize, lastModified: new Date(file.regDt).getTime()}

        const id = this._uppy.addFile({
            id: file.fileId,
            name: file.fileName,
            type: file.fileType,
            data: fileData,
            meta:{
                url: file.fileUrl,
                saved: true,
                fileId: file.fileId
            },
            ...options
        })

        this._uppy.setFileState(id, {
            progress: { uploadComplete: true, uploadStarted: true }
        })
    }

}

class UploadView {
    constructor(id) {
        this._id = id
        this._data = []
    }

    setData(data = []) {
        this._data = data
        return this
    }

    draw() {
       const dom = $(`#${this._id}`)
       dom.empty()

       if(this._data.length === 0) dom.append("파일이 없습니다.")
       else {
           let body = '<ul>'
           this._data.forEach(file => {
               body += `<li style="margin-top: 10px"> <a href="/api/hong/files/download?fileUrl=${file.fileUrl}&download=Y" download="" />${file.fileName}</li>`
           })
           body += '</ul>'
           dom.append(body)
       }
    }
}