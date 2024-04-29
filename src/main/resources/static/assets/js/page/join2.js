
let chkUserId = false
let chkUserEmail = false

const password =$("#password")
const passwordChk = $("#passwordChk")

const userIdInput = $("#userId")
const userIdChkBtn = $("#userId-check")
const userEmailInput = $("#userEmail")
const userEmailChkBtn = $("#userEmail-check")

$(function () {

    $("#go-back-btn").on('click', () => {
        window.location.href = '/login'
    })

    userIdChkBtn.on('click', () => {
        Http.get(`/api/front/user/chkUserId.json`, {userId: userIdInput.val()}).then((res) => {
            if(res['httpStatus'] === 200) {
                Util.alert(res.message.replace("\n", "<br>")).then(() => {
                    userIdInput.removeClass("is-invalid")
                    userIdInput.attr('readonly', true)
                    userIdInput.attr('disabled', true)
                    userIdChkBtn.attr('disabled', true)
                    chkUserId = true
                })
            } else if(res['httpStatus'] === 400) Util.alert(res.message.replace("\n", "<br>"))
        })
    })

    userEmailChkBtn.on('click', () => {
        Http.get(`/api/front/user/chkUserEmail.json`, {userEmail: userEmailInput.val()}).then((res) => {
            if(res.message.key === 0) {
                Util.alert(res.message.message.replace("\n", "<br>")).then(() => {
                    userEmailInput.removeClass("is-invalid")
                    userEmailInput.attr('readonly', true)
                    userEmailInput.attr('disabled', true)
                    userEmailChkBtn.attr('disabled', true)
                    chkUserEmail = true
                })
            } else Util.alert(res.message.message.replace("\n", "<br>"))
        })
    })

    $("#newLogin").on('click', () => {
        const form = document.getElementById("form")
        if (form.checkValidity() === false) form.classList.add("was-validated")
        else {
            if(chkUserId && chkUserEmail) {
                if(password.val() === passwordChk.val()) {

                    const obj = {
                        'role' : 'ROLE_USER',
                        'userId': userIdInput.val(),
                        'password': password.val(),
                        'userEmail': userEmailInput.val(),
                        'userName': $("#userName").val()
                    }

                    Http.post('/api/front/user/insert.json', obj).then((res) => {
                        if(res['httpStatus'] === 201) Util.alert(`${userIdInput.val()}로 회원가입되었습니다.`).then(() => window.location.href = '/login')
                        else Util.alert("죄송합니다. <br> 회원가입에 실패했습니다.")
                    })

                } else Util.alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.")
            } else Util.alert("아이디 및 이메일 중복 확인을 해주세요.")
        }
    })

    password.on('input', validatePassword);
    passwordChk.on('input', validatePassword);
})

function validatePassword() {
    const passwordVal = password.val();
    const confirmPasswordVal = passwordChk.val();
    if(passwordVal && confirmPasswordVal) {
        if (passwordVal === confirmPasswordVal) passwordChk.removeClass('is-invalid');  // 비밀번호와 비밀번호 확인이 일치하는 경우
        else passwordChk.addClass('is-invalid');                                        // 비밀번호와 비밀번호 확인이 일치하지 않는 경우
    }
}