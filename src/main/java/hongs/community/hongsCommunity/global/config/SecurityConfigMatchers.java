package hongs.community.hongsCommunity.global.config;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class SecurityConfigMatchers {

    public static final AntPathRequestMatcher[] BEFORE_LOGIN = new  AntPathRequestMatcher[]{
            new AntPathRequestMatcher("/login")
            ,new AntPathRequestMatcher("/assets/**")
            ,new AntPathRequestMatcher("/api/front/**")
    };

    public static final AntPathRequestMatcher[] ROLE_ADMIN_SUPER = new  AntPathRequestMatcher[]{
            new AntPathRequestMatcher("/api/admin/**")
    };

    public static final AntPathRequestMatcher[] ROLE_SUPER = new  AntPathRequestMatcher[]{
            new AntPathRequestMatcher("/api/super/**")
    };

}
