
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

        if (type === "disable") {
            console.log("disable: 비활성화")
        } else if (type === "expired") {
            console.log("expired: 비밀번호 만료")
        } else if (type === "account") {
            console.log("account: 계정 만료")
        }
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
})