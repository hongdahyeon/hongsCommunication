package hongs.community.hongsCommunity.global.handler;

import hongs.community.hongsCommunity.domain.user.front.service.HongFrontLoginUserService;
import hongs.community.hongsCommunity.domain.user.front.vo.HongCheckUserVo;
import hongs.community.hongsCommunity.global.auth.oatuh2.OAuth2ErrorCode;
import hongs.community.hongsCommunity.global.auth.oatuh2.OAuth2ErrorCustom;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

    private final HongFrontLoginUserService frontLoginUserService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String userId = request.getParameter("userId");
        /* [폼로그인] 비밀번호 오류 */
        if(exception instanceof BadCredentialsException) {
            HongCheckUserVo checkUser = frontLoginUserService.checkUser(userId);
            if(checkUser != null) {
                Integer failCnt = checkUser.getPwdFailCnt() + 1;
                frontLoginUserService.updateFailCnt(userId, failCnt);
                String message = (failCnt == 5) ? "비밀번호 " + failCnt + " / 5 회 오류로 계정이 잠겼습니다. \n 관리자에게 문의 바랍니다." : "비밀번호 " + failCnt + "/5 회 오류";
                sendMssgAndRedirect(message, "error", userId, response);
            } else {
                /* [폼로그인] 사용자 없음 */
                sendMssgAndRedirect(FailureException.UsernameNotFoundException.message, "none", userId, response);
            }
        }else {
            /* [폼로그인] 계정 비활성화 : 관리자가 사용자의 계정 비활성화 */
            if(exception instanceof DisabledException) sendMssgAndRedirect(FailureException.DisabledException.message, "disable", userId, response);

            /* [폼로그인] 비밀번호 만료 : 변경일로부터 90일 지남 */
            if(exception instanceof CredentialsExpiredException) sendMssgAndRedirect(FailureException.CredentialsExpiredException.message, "expired", userId, response);

            /* [폼로그인] 휴먼 계정 : 최근 로그인 시점이 1년 지남 */
            if(exception instanceof AccountExpiredException) sendMssgAndRedirect(FailureException.AccountExpiredException.message, "account", userId, response);

            /* [폼로그인] 비밀번호 5회 오류로 계정 잠김 */
            if(exception instanceof LockedException) sendMssgAndRedirect(FailureException.LockedException.message, "error", userId, response);

            /* [소셜 로그인] */
            if(exception instanceof OAuth2AuthenticationException) {
                OAuth2ErrorCustom error = (OAuth2ErrorCustom) ((OAuth2AuthenticationException) exception).getError();
                OAuth2ErrorCode errorCode = error.getOAuth2ErrorCode();
                String userEmail = error.getUserEmail();
                String socialUserId = error.getUserId();

                /* [소셜 로그인] 이메일 중복 */
                if(OAuth2ErrorCode.socialEmailDuplicate == errorCode) sendMssgAndRedirectSocial(OAuth2ErrorCode.socialEmailDuplicate.message, "socialEmailDuplicate", socialUserId, userEmail, response);

                /* [소셜 로그인] 계정 비활성화  */
                if(OAuth2ErrorCode.socialEnable == errorCode) sendMssgAndRedirectSocial(OAuth2ErrorCode.socialEnable.message, "socialEnable", socialUserId, userEmail, response);

                /* [소셜 로그인] 계정 잠김 */
                if(OAuth2ErrorCode.socialLock == errorCode) sendMssgAndRedirectSocial(OAuth2ErrorCode.socialLock.message, "socialLock", socialUserId, userEmail, response);

                /* [소셜 로그인] 휴먼 계정 : 최근 로그인 시점이 1년 지남 */
                if(OAuth2ErrorCode.socialExpired == errorCode) sendMssgAndRedirectSocial(OAuth2ErrorCode.socialExpired.message, "socialExpired", socialUserId, userEmail, response);

                /* [소셜 로그인] 로그인 과정에서 오류 발생 */
                if(errorCode == null) sendMssgAndRedirectSocial(OAuth2ErrorCode.socialError.message, "socialError", socialUserId, userEmail, response);
            }

            /* [폼로그인] 로그인 과정에서 오류 발생 */
            if(exception instanceof InternalAuthenticationServiceException) sendMssgAndRedirect(FailureException.InternalAuthenticationServiceException.message, "error", userId, response);
        }
    }

    public void sendMssgAndRedirect(String message, String type, String userId, HttpServletResponse response) throws IOException {
        String sendMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);
        response.sendRedirect("/login?type="+type+"&userId="+userId+"&mssg="+sendMessage);
    }

    public void sendMssgAndRedirectSocial(String message, String type, String userId, String userEmail, HttpServletResponse response) throws IOException {
        String sendMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);
        response.sendRedirect("/login?type="+type+"&userId="+userId+"&userEmail="+userEmail+"&mssg="+sendMessage);
    }
}
