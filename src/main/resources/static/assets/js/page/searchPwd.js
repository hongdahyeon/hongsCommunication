
$(function () {

    $("#search-pwd-btn").on('click', (e) => {
        e.preventDefault();
        const form = document.getElementById("form")
        if (form.checkValidity() === false) form.classList.add("was-validated")
        else {
            FormDataToObj.getParameter("form").then((obj) => {
                Http.get('/api/front/user/searchPwd.json', obj).then((res) => {
                    if(res['httpStatus'] === 200) Util.alert(res.message).then(() => window.location.href = '/login')
                    else if(res['httpStatus'] === 400) Util.alert(res.message)
                })
            })
        }
    })

    $("#go-back-btn").on('click', () => {
        window.location.href = '/login'
    })
})