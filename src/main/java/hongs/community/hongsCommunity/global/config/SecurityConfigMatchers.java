package hongs.community.hongsCommunity.global.config;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class SecurityConfigMatchers {

    public static final AntPathRequestMatcher[] BEFORE_LOGIN = new  AntPathRequestMatcher[]{
             new AntPathRequestMatcher("/login")
            ,new AntPathRequestMatcher("/searchId")
            ,new AntPathRequestMatcher("/searchPwd")
            ,new AntPathRequestMatcher("/assets/**")
            ,new AntPathRequestMatcher("/api/front/**")
            ,new AntPathRequestMatcher("/api/swagger-ui/index.html")
            ,new AntPathRequestMatcher("/api-docs")
            ,new AntPathRequestMatcher("/join**")
            ,new AntPathRequestMatcher("/chkUserId.json**")
            ,new AntPathRequestMatcher("/chkUserEmail.json**")
    };

    public static final AntPathRequestMatcher[] ROLE_ADMIN_SUPER = new  AntPathRequestMatcher[]{
            new AntPathRequestMatcher("/api/admin/**")
           ,new AntPathRequestMatcher("/admin/**")
    };

    public static final AntPathRequestMatcher[] ROLE_SUPER = new  AntPathRequestMatcher[]{
             new AntPathRequestMatcher("/api/super/**")
            ,new AntPathRequestMatcher("/super/**")
    };

}
