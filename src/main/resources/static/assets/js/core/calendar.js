class DateCalendar{

    constructor(id) {
        this._initSettings(id)
    }

    _initSettings(id) {
        this._calendarEl = document.getElementById(id)
        this._calendar = null

        // header options
        this._left = 'prev'
        this._center = 'title'
        this._right = 'next'

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
        this._eventChange = null
        this._eventRemove = null
        this._eventClick = null
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
        calendar.on('eventChange', info => this._eventChange(info))
        calendar.on('eventRemove', info => this._eventRemove(info))
        calendar.on('eventClick', info => this._eventClick(info))

        this._calendar = calendar

        return this
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
}