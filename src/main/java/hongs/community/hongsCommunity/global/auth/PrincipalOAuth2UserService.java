package hongs.community.hongsCommunity.global.auth;

import hongs.community.hongsCommunity.domain.menu.HongMenuService;
import hongs.community.hongsCommunity.domain.menu.vo.HongMenuVo;
import hongs.community.hongsCommunity.domain.user.front.service.HongSocialUserFrontService;
import hongs.community.hongsCommunity.domain.user.front.dto.HongSocialUserInsertDto;
import hongs.community.hongsCommunity.domain.user.front.vo.HongLoginUserVo;
import hongs.community.hongsCommunity.global.handler.FailureException;
import hongs.community.hongsCommunity.global.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {

    private final HongSocialUserFrontService socialUserFrontService;
    private final HongMenuService menuService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> userInfo = user.getAttributes();

        String userId = "";
        String email = "";
        String name = "";

        // **
        switch (provider) {
            case "kakao":
                Map<String, Object> kakaoAccount = user.getAttribute("kakao_account");
                userId = provider + "_" + userInfo.get("id");
                email = (String) kakaoAccount.get("email");
                name = (String) ((Map<String, Object>) kakaoAccount.get("profile")).get("nickname");
                break;

            case "naver":
                Map<String, Object> naverAccount = user.getAttribute("response");
                userId = provider + "_" + (String) naverAccount.get("id");
                email = (String) naverAccount.get("email");
                name = (String) naverAccount.get("name");
                break;

            case "google": // **
                userId = provider + "_" + (String) userInfo.get("sub");
                email = (String) userInfo.get("email");
                name = (String) userInfo.get("name");
                break;

        }

        log.info("provider: {} -> userId : {}, name : {}, email : {}", provider, userId, name, email);

        HongLoginUserVo socialUser = socialUserFrontService.findSocialUser(userId);

        if (socialUser == null) {
            Boolean ifUserEmailIsEmpty = socialUserFrontService.findUserEmail(email);
            if(ifUserEmailIsEmpty) {

                socialUser = socialUserFrontService.joinSocialUser(new HongSocialUserInsertDto(userId, name, email));
                this.customUser(socialUser);
                return new PrincipalDetails(socialUser, userInfo);

            } else throw new OAuth2AuthenticationException(new OAuth2Error("socialEmailDuplicate", email, ""), FailureException.OAuth2AuthenticationExceptionEmailDuplicateMsg.message);

        } else {
            if(!socialUser.getIsEnable()) throw new OAuth2AuthenticationException(new OAuth2Error("socialEnable", email, userId), FailureException.DisabledException.message);
            if(!socialUser.getIsNonLocked()) throw new OAuth2AuthenticationException(new OAuth2Error("socialLock", email, userId), FailureException.OAuth2AuthenticationLockedException.message);
            if(!TimeUtil.isXYearAfter(socialUser.getLastLoginDate(), 1)) throw new OAuth2AuthenticationException(new OAuth2Error("socialExpired", email, userId), FailureException.AccountExpiredException.message);
        }

        this.customUser(socialUser);
        return new PrincipalDetails(socialUser, userInfo);
    }

    public void customUser(HongLoginUserVo user) {
        List<HongMenuVo> list = menuService.list(user.getRole());
        user.setMenu(list);
    }
}
