package hongs.community.hongsCommunity.global.handler;

import hongs.community.hongsCommunity.domain.user.service.HongLoginUserService;
import hongs.community.hongsCommunity.domain.user.vo.HongCheckUserVo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

    private final HongLoginUserService userService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String userId = request.getParameter("userId");
        log.info("Login failure userId : {} ", userId);

        if(exception instanceof BadCredentialsException) {
            HongCheckUserVo checkUser = userService.checkUser(userId);
            if(checkUser != null) userService.updateFailCnt(userId, checkUser.getPwdFailCnt());
            else {
                log.info("no user");
            }
        }
    }
}
