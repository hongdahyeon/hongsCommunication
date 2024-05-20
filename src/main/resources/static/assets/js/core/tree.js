class Tree {
    constructor(id) {
        this._initialValues(id)
    }
    _initialValues(id){
        this._id = id
        this._url = ''
        this._queryString = {}
        this._check_callback = true
        this._nodeClick = null
        this._js_tree = null
        this._refresh = null
    }

    setUrl(url) {
        this._url = url
        return this
    }

    setParams(obj = {}) {
        this._queryString = obj
        return this
    }

    changeCallBack() {
        this._check_callback = false
        return this
    }

    nodeClick(callback) {
        this._nodeClick = callback
        return this
    }

    refresh(){
        this._js_tree.jstree("refresh")
    }

    getSelectNode() {
        const ref = this._js_tree.jstree(true)
        const sel = ref.get_selected(true)[0]
        return {ref, sel}
    }

    deselectNode(id) {
        this._js_tree.jstree(true).deselect_node(id)
    }

    selectNode(id) {
        this._js_tree.jstree(true).select_node(id)
    }

    getNode(id) {
        return this._js_tree.jstree(true).get_node(id)
    }

    deleteNode(id) {
        this._js_tree.jstree(true).delete_node(id)
    }

    addNode(ref, id, node, first = true) {
        const newMenu = this.getNode(node.id)
        if(newMenu) this.deleteNode(node.id)
        ref.create_node(id, node, first ? 'first' : 'last')
    }

    init() {
        const js_tree = $(`#${this._id}`).jstree({
            'core': {
                check_callback: this._check_callback,
                data: (obj, callback) => this.getTreeData(obj, callback)
            }
        })

        js_tree.on("select_node.jstree", (e, data) => this._nodeClick(e, data))

        this._js_tree = js_tree

        return this
    }

    getTreeData = (obj, callback) => {
        Http.get(this._url, this._queryString).done((result) => {
            const dataList = result.message;
            let dataMap = {};
            let data = [];
            for (let i = 0; i < dataList.length; i++) {
                const node = {
                    id: dataList[i]['menuUid'],
                    text: dataList[i]['menuNm'],
                    data: dataList[i],
                    state: { opened: true }
                }

                dataMap[node.id] = node

                // 루트일 때
                if (!dataList[i]['upperMenuUid']) {
                    node.children = []
                    data.push(node)
                } else {
                    let parent = dataMap[node.data['upperMenuUid']];
                    if (!parent) continue;
                    if (!parent.children) parent.children = [];
                    parent.children.push(node);
                }
            }
            callback(data);
        })
    }
}