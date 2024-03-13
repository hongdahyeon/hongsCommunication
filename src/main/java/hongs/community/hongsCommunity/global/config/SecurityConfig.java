package hongs.community.hongsCommunity.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity              // spring security 활성화 어노테이션
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private static final String LOGIN = "/login";
    private static final String LOGOUT = "/logout";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable();       // csrf 및 frame 옵션 비활성화 -> 애플리케이션을 iframe에 임베드 가능하도록 한다.

        http
            .authorizeHttpRequests(authorize ->
                authorize
                        .requestMatchers(SecurityConfigMatchers.BEFORE_LOGIN).permitAll()
                        .requestMatchers(SecurityConfigMatchers.ROLE_ADMIN_SUPER).hasAnyAuthority("ROLE_ADMIN", "ROLE_SUPER")
                        .requestMatchers(SecurityConfigMatchers.ROLE_SUPER).hasAnyAuthority("ROLE_SUPER")
                        .anyRequest().authenticated()
            )
            .formLogin(form ->
                form
                    .loginPage(LOGIN)
                    .usernameParameter("userId")
                    .loginProcessingUrl("/loginProc")
                    .defaultSuccessUrl("/")
            )
            .logout(logout ->
                logout
                    .logoutUrl(LOGOUT)
                    .logoutSuccessUrl(LOGIN)
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
            );
        return http.build();
    }
}
