class DateCalendar{

    constructor(id) {
        this._initSettings(id)
    }

    _initSettings(id) {
        this._id = id
        this._calendarEl = document.getElementById(id)
        this._calendar = null
        this._calInfo = null

        this._data = {
            title: '',
            uid: '',
            start: '',
            end: '',
            backgroundColor: '',
            borderColor: '',
            textColor: '',
            allDay: true
        }

        // header options
        this._left = 'prev today'
        this._center = 'title'
        this._right = 'dayGridMonth,dayGridWeek,dayGridDay next'

        // calendar option
        this._height = '700px'                      // 캘린더 높이
        this._expandRows = true                     // 화면에 맞게 높이 재설정
        this._initialView = 'dayGridMonth'          // dayGridMonth 'dayGridWeek', 'timeGridDay', 'listWeek'
        this._locale = 'ko'                         // 언어
        this._selectable = true                     // 영역 선택 가능 여부
        this._editable = true                       // 편집 가능 여부
        this._dayMaxEventRows = true                // row 높이보다 많으면 +숫자 링크 보임

        // click event
        this._select = null
        this._eventChange = ''
        this._eventRemove = null
        this._eventClick = null
        this._eventDrop = null
    }

    init(){
        const options = {
            height: this._height,
            expandRows: this._expandRows,
            headerToolbar: {
                left: this._left,
                center: this._center,
                right: this._right
            },
            initialView: this._initialView,
            locale: this._locale,
            selectable: this._selectable,
            editable: this._editable,
            dayMaxEventRows: this._dayMaxEventRows,
            events: function(info, successCallback, failureCallback) {
                Http.syncGet('/api/calendar/list.json').then((res) => {
                    successCallback(res.message)
                })
            }
        }

        const calendar = new FullCalendar.Calendar(this._calendarEl, options)
        calendar.render()

        calendar.on("select", info => this._select(info))
        // calendar.on('eventChange', info => this._eventChange(info))
        calendar.on('eventRemove', info => this._eventRemove(info))
        calendar.on('eventClick', info => this._eventClick(info))
        calendar.on('eventDrop', info => this._eventDrop(info))

        this._calendar = calendar

        return this
    }

    setData(key, value) {
        this._data[key] = value
        return this
    }

    resetData() {
        Object.keys(this._data).forEach(key => { if(key !== 'allDay') this._data[key] = '' });
    }

    getData() {
        return this._data
    }

    setInfo(info) {
        this._calInfo = info
    }

    getInfo() {
        return this._calInfo
    }

    rowSelect(callback) {
        this._select = callback
        return this
    }

    eventChange(callback) {
        this._eventChange = callback
        return this
    }

    eventRemove(callback) {
        this._eventRemove = callback
        return this
    }

    eventClick(callback) {
        this._eventClick = callback
        return this
    }

    addDateEvent(obj) {
        this._calendar.addEvent(obj)
    }

    eventDrop(callback) {
        this._eventDrop = callback
        return this
    }

    updateDateEvent(obj) {
        this._calInfo.event.setProp('title', obj.title)
        this._calInfo.event.setStart(obj.start)
        this._calInfo.event.setEnd(obj.end)
        this._calInfo.event.setProp('borderColor', obj.borderColor)
        this._calInfo.event.setProp('backgroundColor', obj.backgroundColor)
        this._calInfo.event.setProp('textColor', obj.textColor)
    }

    refresh() {
        this._calendar('refetchEvents')
    }
}