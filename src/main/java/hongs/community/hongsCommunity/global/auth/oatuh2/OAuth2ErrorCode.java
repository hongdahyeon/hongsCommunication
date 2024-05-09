package hongs.community.hongsCommunity.global.auth.oatuh2;

public enum OAuth2ErrorCode {

    socialEmailDuplicate,       // 소셜 회원가입시 이메일 중복
    socialEnable,               // 소셜 로그인시 계정 비활성화
    socialLock,                 // 소셜 로그인시 계정 잠김
    socialExpired,              // 소셜 로그인시 계정 만료 (최근 로그인 일자 1년 넘음)
    socialError                 // 소셜 로그인 오류
}
