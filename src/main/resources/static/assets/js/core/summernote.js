class Summernote {
    constructor(id, height = 300) {
        this._initSettings(id, height)
        this._init()
    }

    _initSettings(id, height){
        this._editor = $(`#${id}`)
        this._height = height
        this._minHeight = null
        this._maxHeight = null
        this._focus = true
        this._placeholder = ''
        this._fontNames = ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', 'Helvetica Neue', 'Helvetica', 'Impact', 'Lucida Grande', 'Tahoma', 'Times New Roman', 'Verdana']
        this._fontSizes = ['8', '9', '10', '11', '12', '14', '16', '18', '20', '22', '24', '36']
        this._style = ['bold', 'italic', 'underline', 'clear']
        this._font = ['strikethrough', 'superscript', 'subscript']
        this._fontsize = ['fontsize']
        this._color = ['color']
        this._para = ['ul', 'ol', 'paragraph']
        this._heights = ['height']
        this._table = ['table']
        this._insert = ['picture']
        this._view = ['codeview', 'help']
    }

    _init() {
        this._editor.summernote({
            height: this._height,
            minHeight: this._minHeight,
            maxHeight: this._maxHeight,
            focus: this._focus,
            placeholder: this._placeholder,
            toolbar: [
                ['style', this._style], ['font', this._font],
                ['fontsize', this._fontsize], ['color', this._color],
                ['para', this._para], ['height', this._heights],
                ['table', this._table], ['insert', this._insert], ['view', this._view]
            ],
            fontNames: this._fontNames,
            fontSizes: this._fontSizes,
            callbacks: {
                onImageUpload: (files) => this.onImageUpload(files),
                onPaste: (e) => this.onPaste(e),
                onMediaDelete: (target) => this.onMediaDelete(target)
            }
        })
    }

    onImageUpload(files) {
        Array.from(files).forEach(x => this.uploadSummernoteImageFile(x));
    }

    onPaste(e) {
        const clipboardData = e.originalEvent.clipboardData;
        const textData = clipboardData.getData("Text");
        if (textData) {
            const bufferText = ((e.originalEvent || e).clipboardData || window.clipboardData).getData('Text')
            e.preventDefault();
            document.execCommand('insertText', false, bufferText);
        }
    }

    onMediaDelete(target) {
        const mpath = $(target[0]).attr('src').replace("..", "");
        $('#summernote_mpath').val(mpath);
    }

    uploadSummernoteImageFile(file){
        let data = new FormData();
        data.append("file", file);
        Http.filePost("/api/upload/summernote/img", data).then((res) => {
            this._editor.summernote('insertImage', res.url)
        })
    }

    getCode() {
        return this._editor.summernote('code')
    }

    reset(){
        this._editor.summernote('reset')
    }

    enable() {
        this._editor.summernote('enable')
    }

    disable(){
        this._editor.summernote('disable')
    }

    destroy() {
        this._editor.summernote('destroy')
    }

    setCode(code){
        this._editor.summernote('code', code)
    }

}