package hongs.community.hongsCommunity.global.handler;

import hongs.community.hongsCommunity.domain.user.front.service.HongFrontLoginUserService;
import hongs.community.hongsCommunity.domain.user.front.vo.HongCheckUserVo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

    private final HongFrontLoginUserService frontLoginUserService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String userId = request.getParameter("userId");
        if(exception instanceof BadCredentialsException) {
            HongCheckUserVo checkUser = frontLoginUserService.checkUser(userId);
            if(checkUser != null) {
                Integer failCnt = checkUser.getPwdFailCnt() + 1;
                frontLoginUserService.updateFailCnt(userId, failCnt);
                String message = (failCnt == 5) ? "비밀번호 " + failCnt + " / 5 회 오류로 계정이 잠겼습니다. \n 관리자에게 문의 바랍니다." : "비밀번호 " + failCnt + "/5 회 오류";
                sendMssgAndRedirect(message, "error", userId, response);
            }
        }else {
            if(exception instanceof DisabledException) sendMssgAndRedirect(FailureException.DisabledException.message, "disable", userId, response);
            if(exception instanceof CredentialsExpiredException) sendMssgAndRedirect(FailureException.CredentialsExpiredException.message, "expired", userId, response);
            if(exception instanceof AccountExpiredException) sendMssgAndRedirect(FailureException.AccountExpiredException.message, "account", userId, response);
            if(exception instanceof LockedException) sendMssgAndRedirect(FailureException.LockedException.message, "error", userId, response);
            if(exception instanceof OAuth2AuthenticationException) {
                OAuth2Error error = ((OAuth2AuthenticationException) exception).getError();
                String errorCode = error.getErrorCode();
                String userEmail = error.getDescription();
                String socialUserId = error.getUri();
                if("socialEmailDuplicate".equals(errorCode)) sendMssgAndRedirectSocial(exception.getMessage(), "socialEmailDuplicate", socialUserId, userEmail, response);
                if("socialEnable".equals(errorCode)) sendMssgAndRedirectSocial(exception.getMessage(), "socialEnable", socialUserId, userEmail, response);
                if("socialLock".equals(errorCode)) sendMssgAndRedirectSocial(exception.getMessage(), "socialLock", socialUserId, userEmail, response);
                if("socialExpired".equals(errorCode)) sendMssgAndRedirectSocial(exception.getMessage(), "socialExpired", socialUserId, userEmail, response);
                if(exception.getMessage() == null) sendMssgAndRedirectSocial(FailureException.OAuth2AuthenticationException.message, "socialError", socialUserId, userEmail, response);
            }
            if(exception instanceof InternalAuthenticationServiceException) sendMssgAndRedirect(FailureException.InternalAuthenticationServiceException.message, "error", userId, response);
        }
    }

    public void sendMssgAndRedirect(String message, String type, String userId, HttpServletResponse response) throws IOException {
        String sendMessage = URLEncoder.encode(message, "UTF-8");
        response.sendRedirect("/login?type="+type+"&userId="+userId+"&mssg="+sendMessage);
    }

    public void sendMssgAndRedirectSocial(String message, String type, String userId, String userEmail, HttpServletResponse response) throws IOException {
        String sendMessage = URLEncoder.encode(message, "UTF-8");
        response.sendRedirect("/login?type="+type+"&userId="+userId+"&userEmail="+userEmail+"&mssg="+sendMessage);
    }
}
