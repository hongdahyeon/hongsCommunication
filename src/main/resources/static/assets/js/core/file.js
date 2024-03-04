
class Upload {

    constructor(selector) {
        this._selector = selector
        this.upload_uuid = this.getUUID();
        this._removes = []
        this._uid = null

        const { Dashboard, Tus } = Uppy
        this._uppy = new Uppy.Uppy({
            locale,
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
            console.log("upload-removed")
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
            if( !file.meta.saved ) {
                Http.delete(`/api/tus/files/temp/delete.json`, { fileUrl: fileUrl })
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

}