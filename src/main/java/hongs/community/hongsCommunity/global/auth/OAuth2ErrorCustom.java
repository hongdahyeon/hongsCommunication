package hongs.community.hongsCommunity.global.auth;

import org.springframework.security.oauth2.core.OAuth2Error;

public class OAuth2ErrorCustom extends OAuth2Error {
    public OAuth2ErrorCustom(String errorCode) {
        super(errorCode);
    }

    public OAuth2ErrorCustom(String errorCode, String userEmail, String userId) {
        super(errorCode, userEmail, userId);
    }
}
