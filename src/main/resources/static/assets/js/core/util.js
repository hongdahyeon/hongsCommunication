class FormDataToObj {
    static async getParameter(formId) {
        const form = document.getElementById(formId);
        const formData = new FormData(form);
        return Array.from(formData.entries()).reduce((perv, [key, value]) => {
            if (perv[key]) {
                Array.isArray(perv[key]) ? perv[key].push(value) : (perv[key] = [perv[key], value]);
            } else {
                perv[key] = value;
            }
            return perv;
        }, {});
    }
}

class Util {
    static alert(html, icon = 's', btn = 's') {
        return Swal.fire({
            html,
            icon: (icon === 's') ? 'success' : 'warning',
            confirmButtonColor: (btn === 's') ? '#3085d6' : '#d33',
            focusConfirm: false,
            confirmButtonText: "확인"
        });
    }

    static confirm(html, icon = 'w', confirmButtonText = '확인', cancelButtonText = '취소') {
        return Swal.fire({
            html,
            icon: (icon === 'w') ? 'warning' : 'success',
            focusConfirm: false,
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: confirmButtonText,
            cancelButtonText: cancelButtonText,
        }).then((res) => res.isConfirmed)
    }
}

class DateUtil {

    static dayDifference(start, end, dateCnt = 1) {
        const startDate = new Date(start)
        const endDate = new Date(end)
        const diffTime = Math.abs(endDate - startDate);
        const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
        return diffDays === dateCnt;
    }

    static getPrevDates(dateStr) {
        const date = new Date(dateStr);
        const oneDay = 24 * 60 * 60 * 1000; // 1일을 밀리초로 표현
        const prevDate = new Date(date.getTime() - oneDay);
        return prevDate.toISOString().split('T')[0]
    }

    static getNextDates(dateStr) {
        const date = new Date(dateStr);
        const oneDay = 24 * 60 * 60 * 1000; // 1일을 밀리초로 표현
        const nextDate = new Date(date.getTime() + oneDay);
        return nextDate.toISOString().split('T')[0]
    }

    static formatDate(dateStr) {
        const date = new Date(dateStr);
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1
        const day = String(date.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
    }
}

class Http {

    static handleError(e) {
        if (e.status === 401) {
            Util.alert("다시 로그인 해주세요.").then(() => {
                window.location.href = '/';
            })
        } else {
            console.error('DataTables AJAX Error: ', e);
        }
    }

    static get(url, params = '', method = 'GET') {
        return $.ajax({
            type: method,
            url: url,
            data: params,
            dataType: 'json',
            beforeSend: function(xhr) {
                const {token, header} = Http.getCookieInfo();
                xhr.setRequestHeader(header, token)
            }
        }).catch(e => this.handleError(e))
    }

    static syncGet(url, params='', async = false, method = 'GET'){
        return $.ajax({
            type: method,
            url: url,
            data: params,
            async: false,
            dataType: 'json',
            beforeSend: function(xhr) {
                const {token, header} = Http.getCookieInfo();
                xhr.setRequestHeader(header, token)
            }
        }).catch(e => this.handleError(e))
    }

    static post(url, data, method = 'POST') {
        return $.ajax({
            type: method,
            url: url,
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json',
            beforeSend: function(xhr) {
                const {token, header} = Http.getCookieInfo();
                xhr.setRequestHeader(header, token)
            }
        }).catch(e => this.handleError(e))
    }

    static delete(url, data, method = 'DELETE') {
        return $.ajax({
            type: method,
            url: url,
            data: data,
            contentType: 'application/json',
            beforeSend: function(xhr) {
                const {token, header} = Http.getCookieInfo();
                xhr.setRequestHeader(header, token)
            }
        }).catch(e => this.handleError(e))
    }

    static deleteUrl(url, method = 'DELETE') {
        return $.ajax({
            type: method,
            url: url,
            contentType: 'application/json',
            beforeSend: function(xhr) {
                const {token, header} = Http.getCookieInfo();
                xhr.setRequestHeader(header, token)
            }
        }).catch(e => this.handleError(e))
    }


    static put(url, data, method='PUT') {
        return $.ajax({
            type: method,
            url: url,
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json',
            beforeSend: function(xhr) {
                const {token, header} = Http.getCookieInfo();
                xhr.setRequestHeader(header, token)
            }
        }).catch(e => this.handleError(e))
    }

    static filePost(url, formData, method = 'POST'){
        return $.ajax({
            type: method,
            url: url,
            data: formData,
            contentType: false,
            processData: false,
            beforeSend: function(xhr) {
                const {token, header} = Http.getCookieInfo();
                xhr.setRequestHeader(header, token)
            }
        }).catch(e => this.handleError(e))
    }

    static fileDownload(id){
        let filename = ''
        return $.ajax({
            url: `/api/downloadFile/${id}`,
            method: 'GET',
            xhrFields: {
                responseType: 'blob'
            },
            beforeSend: function(xhr) {
                const {token, header} = Http.getCookieInfo();
                xhr.setRequestHeader(header, token)
            },
            success: function (blobData, status, xhr) {
                const contentDisposition = xhr.getResponseHeader('Content-Disposition');
                const matches = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/.exec(contentDisposition);
                filename = matches && matches[1] ? matches[1].replace(/['"]/g, '').replace('UTF-8', '') : "";

                if (filename) {
                    const link = $('<a style="display: none;"></a>');
                    link.attr('href', window.URL.createObjectURL(blobData));
                    link.attr('download', decodeURIComponent(filename));
                    $('body').append(link);
                    link[0].click();
                    link.remove();
                }
            }
        }).catch(e => this.handleError(e))
    }

    static getCookieInfo(){
        const token = $("meta[name='_csrf']").attr("content")
        const header = $("meta[name='_csrf_header']").attr("content");
        return { header : header, token : token }
    }

}
