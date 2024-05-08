package hongs.community.hongsCommunity.global.handler;

import hongs.community.hongsCommunity.domain.user.front.service.HongFrontLoginUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final HongFrontLoginUserService frontLoginUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String userId = request.getParameter("userId");
        if(userId == null) {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            Map<String, Object> map = oAuth2User.getAttribute("response");
            String userEmail = (String) map.get("email");
            log.info("Login success email : {} ", userEmail);
            frontLoginUserService.updatePwdChangeDateAndLoginDate(userEmail);
        } else {
            log.info("Login success userId : {} ", userId);
            frontLoginUserService.resetFailCntAndLastLoginDate(userId);
        }

        response.sendRedirect("/");
    }
}
