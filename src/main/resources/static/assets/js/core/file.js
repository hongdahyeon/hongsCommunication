class Upload {
    constructor(selector) {
        this._selector = selector
        this.upload_uuid = this.getUUID();
        this._removes = []
        this._uid = null

        const { Dashboard, Tus } = Uppy
        this._uppy = new Uppy.Uppy({
            locale,
            autoProceed: true,
            onBeforeFileAdded: (file) => {
                if(this._uid != null) {
                    file.meta = {
                        ...file.meta,
                        uid: this._uid
                    }
                }
                return file
            }
        })

        this._uppy.use(Dashboard,{
            inline: true,
            target: selector,
            width: '100%',
            height: 400,
            showRemoveButtonAfterComplete: true
        })

        this._uppy.use(Tus, {
            endpoint: `/api/tus/files/upload?uploadId=${this.upload_uuid}`,
            onShouldRetry: this._onShouldRetry
        })

        this._uppy.on('upload-success', (file, upload) => {
            console.log("upload-success")
            file.meta.url = upload.uploadURL
            file.meta.saved = false
            if( !file.meta.saved ) {
                file.meta.name = `[임시] ${file.name}`
            }
        })

        this._uppy.on('upload-error', (file, responseObject) => {
            console.log("upload-error")
        })

        this._uppy.on('file-removed', (file, reason) => {
            console.log("file-removed")
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
        console.log('file: ', file)
        const fileUrl = file.meta.url
        if(reason === 'removed-by-user') {
            if( !file.meta.saved ) {
                Http.delete(`/api/tus/files/temp/delete.json?fileUrl=${fileUrl}`)
            } else this._removes.push(fileUrl)
        }
    }

    setUid(fileUid) {
        this._uid = fileUid
    }

    getTempFileUrls(){
        return this._uppy.getFiles().filter(x => !x.meta.saved).map(x => x.meta.url)
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
                    list.forEach(data => upload._addStaticFile(data, (data.saved !== 'SAVED')))
                } else file.container.html(file.textEmpty)
            })
        } else {
            upload.setUid(null)
            file.container.html(file.textEmpty)
        }
    }

    _addStaticFile(file, isTemp) {
        const options = {}

        if( file.fileType.toString().includes('image') ) {
            options.preview = `/api/hong/files/download?fileUrl=${file.fileUrl}`
        }

        const name = `${isTemp? '[임시]' : ''}${file.fileName}`
        const id = this._uppy.addFile({
            id: file.fileId,
            name: name,
            type: file.fileType,
            data: {
                size: file.fileSize,
                lastModified: new Date(file.regDt).getTime()
            },
            meta:{
                url: file.fileUrl,
                saved: !isTemp,
                fileId: file.fileId
            },
            ...options
        })

        this._uppy.setFileSate(id, {
            progress:{
                uploadComplete: true,
                uploadStarted: true
            }
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