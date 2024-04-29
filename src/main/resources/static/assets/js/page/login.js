
const pwdError = $("#pwdError")
const idItemKey = 'HONG_COMMUNITY_ID'

$(document).ready(function(e) {

    const params = new URLSearchParams(location.search)
    if(params.get("type") !== null) {
        const type = decodeURIComponent(params.get("type"))
        const errorMessage = decodeURIComponent(params.get("mssg"))
        const userId = decodeURIComponent(params.get("userId"))
        history.replaceState({}, null, '/login')

        pwdError.html(errorMessage.replace("\n", "<br>"))

        /* [비밀번호 만료] : 비밀번호 변경한지 90일이 지났을때 */
        if(type === "expired") {
            Util.alert(errorMessage.replace("\n", "<br>")).then(() => {

                const changePwd = $("#changePwd")
                const changePwdChk = $("#changePwdChk")
                changePwd.on('input', () => validatePassword(changePwd, changePwdChk));
                changePwdChk.on('input', () => validatePassword(changePwd, changePwdChk));

                $("#changePwdModal-loginUserId").val(userId)
                $("#changePwdModal").modal('show')
            })
        }

        /* [소셜 로그인 실패], [비밀번호 5회 틀림으로 인한 계정 잠김], [내부 오류] */
        if(type === "error") Util.alert(errorMessage.replace("\n", "<br>"))

        /* [계정 비활성화] : 관리자가 계정을 비활성화 시킴 */
        if(type === "disable") Util.alert(errorMessage.replace("\n", "<br>"))

        /* [계정 만료] : 로그인 1년이상 안해서 휴면 계정된 경우 */
        if(type === "account") {
            Util.alert(errorMessage.replace("\n", "<br>")).then(() => {
                $("#validateEmailModal-loginUserId").val(userId)
                $("#validateEmailModal").modal('show')
            })
        }

        /* [소셜로그인 오류] : (현시점) 소셜 로그인 이메일이 중복되는 경우 */
        if(type === "socialError") Util.alert(errorMessage.replace("\n", "<br>"))
    }

    /* 로그인 아이디 기억하기 -> localStorage에서 id가져오기 */
    document.getElementById('form').addEventListener('submit', () => {
        $('#remember').is(":checked") && localStorage.setItem(idItemKey, $('#userId').val())
        return true
    })

    /* 로그인 아이디 기억하기 */
    const rememberId = localStorage.getItem(idItemKey);
    if (rememberId) {
        const checkbox = $("#remember");
        checkbox.prop("checked", !checkbox.prop("checked"));
        $('#userId').val(rememberId)
    }

    /* 로그인 -> 비번 보이기/숨기기 */
    $("#show-pwd").click(() => {
        const passwordInput = $('#password');
        const type = passwordInput.attr('type') === 'password' ? 'text' : 'password';
        const text = passwordInput.attr('type') === 'password' ? 'hide' : 'show';
        passwordInput.attr('type', type);
        $("#show-pwd").text(text)
    })

    /* 카카오톡 로그인 */
    $("#kakao-login-btn").on("click", () => window.location.href = '/oauth2/authorization/kakao')

    /* 네이버 로그인 */
    $("#naver-login-btn").on("click", () => window.location.href = '/oauth2/authorization/naver')

    /* 구글 로그인 */
    $("#google-login-btn").on("click", () => window.location.href = '/oauth2/authorization/google')

    /* 아이디 찾기 */
    $("#search-id").on('click', () => window.location.href = '/searchId')

    /* 비밀번호 찾기 */
    $("#search-pwd").on('click', () => window.location.href = '/searchPwd')

    /* 회원가입 하기 */
    $("#new-login").on('click', () => window.location.href = '/join1')

    /* [비밀번호 만료] 90일 연장 */
    $("#more-days").on('click', () => {
        const userId = $("#changePwdModal-loginUserId").val()
        const obj = {userId: userId, chngPwd: false}
        Http.put('/api/front/user/updatePwd.json', obj).then((res) => {
            if(res['httpStatus'] === 200) {
                Util.alert("비밀번호 만료일이 90일 연장되었습니다. <br> 다시 로그인해주세요.")
                    .then(() => window.location.href = '/login')
            } else Util.alert("비밀번호 변경에 실패했습니다.")
        })
    })

    /* [비밀번호 만료] 비밀번호 변경하기 */
    $("#change-pwd").on('click', () => {
        const userId = $("#changePwdModal-loginUserId").val()
        const password = $("#changePwd").val()
        const form = document.getElementById("change-pwd-form")
        if (form.checkValidity() === false) form.classList.add("was-validated")
        else {
            const obj = {userId: userId, chngPwd: true, password: password}
            Http.put('/api/front/user/updatePwd.json', obj).then((res) => {
                if (res['httpStatus'] === 200) {
                    Util.alert("비밀번호가 변경되었습니다. <br> 다시 로그인해주세요.")
                        .then(() => window.location.href = '/login')
                } else Util.alert("비밀번호 변경에 실패했습니다.")
            })
        }
    })

    /* [계정 만료] 이메일로 인증번호 발송 */
    $("#email-validate").on('click', function(e) {
        const form = document.getElementById("validate-email-form");
        if (form.checkValidity() === false) form.classList.add("was-validated");
        else {
            const userId = $("#validateEmailModal-loginUserId").val()
            const userEmail = $("#chkEmail").val()
            const obj = {userId: userId, userEmail: userEmail}
            Http.get('/api/front/user/sendEmail.json', obj).then((res) => {
                if(res['httpStatus'] === 400) Util.alert(res.message.replace("\n", "<br>"))
                else if(res['httpStatus'] === 200) {
                    Util.alert(res.message.replace("\n", "<br>")).then(() => {
                        $("#email-validate-chk").prop('disabled', false)
                        $("#email-validate").prop('disabled', true)
                    })
                }
            })
        }
    })

    /* [계정 만료] 이메일로 인증번호 발송 */
    $("#email-validate-chk").on('click', function(e) {
        const userId = $("#validateEmailModal-loginUserId").val()
        const userEmail = $("#chkEmail").val()
        const verifyCode = $("#verify-code").val()

        if(!verifyCode.length) Util.alert("이메일로 받으신 인증번호를 입력해주세요.")
        else {
            const obj = {userId: userId, userEmail: userEmail, verifyCode: verifyCode}
            Http.get('/api/front/verify/check-verify.json', obj).then((res) => {
                if(res['httpStatus'] === 400) Util.alert(res.message.replace("\n", "<br>"))
                else if(res['httpStatus'] === 200) {
                    Util.alert(res.message.replace("\n", "<br>")).then(() => {
                        window.location.href = '/login'
                    })
                }
            })
        }
    })
})

function validatePassword(password, confirmPassword) {
    const passwordVal = password.val();
    const confirmPasswordVal = confirmPassword.val();
    if(passwordVal && confirmPasswordVal) {
        if (passwordVal === confirmPasswordVal) confirmPassword.removeClass('is-invalid');  // 비밀번호와 비밀번호 확인이 일치하는 경우
        else confirmPassword.addClass('is-invalid');                                        // 비밀번호와 비밀번호 확인이 일치하지 않는 경우
    }
}