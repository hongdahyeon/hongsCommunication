package hongs.community.hongsCommunity.global.handler;

import hongs.community.hongsCommunity.domain.menu.HongMenuService;
import hongs.community.hongsCommunity.domain.user.front.service.HongFrontLoginUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final HongFrontLoginUserService frontLoginUserService;
    private final HongMenuService menuService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String userId = request.getParameter("userId");
        if(userId == null) {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            Map<String, Object> map = new HashMap<>();
            String userEmail = "";

            map = oAuth2User.getAttribute("response");
            if(map == null) {
                map = oAuth2User.getAttribute("kakao_account");
                if(map == null) userEmail = oAuth2User.getAttribute("email");
                else userEmail = (String) map.get("email");
            } else userEmail = (String) map.get("email");

            log.info("[로그인 성공] 이메일: {} ", userEmail);
            frontLoginUserService.updatePwdChangeDateAndLoginDate(userEmail);
        } else {
            log.info("[로그인 성공] 아이디: {} ", userId);
            frontLoginUserService.resetFailCntAndLastLoginDate(userId);
        }

        String landingPage = "/";

        Collection authorities = authentication.getAuthorities();
        if (!authorities.isEmpty()) {
            GrantedAuthority next = (GrantedAuthority) authorities.iterator().next();
            String authority = next.getAuthority();
            String landPage = menuService.getLandingPageById(authority);
            landingPage = (landPage != null) ? landPage : "/";
            log.info("랜딩페이지 : {} ", landingPage);
        }

        response.sendRedirect(landingPage);
    }
}
