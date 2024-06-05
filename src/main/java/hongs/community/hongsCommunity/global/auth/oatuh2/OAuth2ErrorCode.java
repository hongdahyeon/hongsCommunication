package hongs.community.hongsCommunity.global.auth.oatuh2;

public enum OAuth2ErrorCode {

    socialEmailDuplicate("이메일이 중복됩니다. \n 다른 이메일로 로그인 해주세요."),
    socialEnable("계정이 비활성화 되었습니다. \n 관리자에게 문의 바랍니다."),
    socialLock("관리자에 의해 계정이 잠겼습니다. \n 관리자에게 문의해주세요."),
    socialExpired("최근 로그인일로부터 1년이 지나 \n  휴먼 계정이 되었습니다. \n 이메일 인증을 해주세요."),
    socialError("소셜 로그인 과정에서 오류가 발생했습니다. \n 관리자에게 문의해주세요.");

    public String message;

    OAuth2ErrorCode(String message) {
        this.message = message;
    }
}
