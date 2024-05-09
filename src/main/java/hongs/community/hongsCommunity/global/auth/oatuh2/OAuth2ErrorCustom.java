package hongs.community.hongsCommunity.global.auth.oatuh2;

import org.springframework.security.oauth2.core.OAuth2Error;

public class OAuth2ErrorCustom extends OAuth2Error {

    private OAuth2ErrorCode oAuth2ErrorCode;
    private String userId;
    private String userEmail;

    public OAuth2ErrorCustom(String errorCode, String description, String uri) {
        super(errorCode, description, uri);
    }

    public OAuth2ErrorCustom(String errorCode) {
        super(errorCode);
    }

    public OAuth2ErrorCustom(OAuth2ErrorCode oAuth2ErrorCode, String userEmail, String userId) {
        super(oAuth2ErrorCode.name());
        this.oAuth2ErrorCode = oAuth2ErrorCode;
        this.userEmail = userEmail;
        this.userId = userId;
    }

    public OAuth2ErrorCode getOAuth2ErrorCode() {
        return this.oAuth2ErrorCode;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public String getUserId() {
        return this.userId;
    }
}