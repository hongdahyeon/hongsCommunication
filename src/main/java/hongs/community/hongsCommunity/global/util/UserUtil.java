package hongs.community.hongsCommunity.global.util;

import hongs.community.hongsCommunity.domain.user.front.vo.HongLoginUserVo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public class UserUtil {

    public static final String REQUEST_USER_KEY = "hong-user";

    public static HongLoginUserVo getLoginUser() {
        return getUser(WebUtil.nowRequest());
    }

    public static Long getLoginUserUid() {
        HongLoginUserVo user = getUser(WebUtil.nowRequest());
        return user.getUserUid();
    }

    public static HongLoginUserVo getUser(HttpServletRequest request) {
        Object user = request.getAttribute(REQUEST_USER_KEY);
        return user != null ? (HongLoginUserVo) user : null;
    }

    public static boolean isAuthenticated(Authentication authentication) {
        return authentication != null && authentication.getPrincipal() != "anonymousUser";
    }

}
