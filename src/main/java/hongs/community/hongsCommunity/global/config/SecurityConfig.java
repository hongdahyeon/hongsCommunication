package hongs.community.hongsCommunity.global.config;

import hongs.community.hongsCommunity.global.auth.PrincipalOAuth2UserService;
import hongs.community.hongsCommunity.global.handler.CustomLoginFailureHandler;
import hongs.community.hongsCommunity.global.handler.CustomLoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity              // spring security 활성화 어노테이션
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomLoginSuccessHandler successHandler;
    private final CustomLoginFailureHandler failureHandler;
    private final PrincipalOAuth2UserService principalOAuth2UserService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private static final String LOGIN = "/login";
    private static final String LOGOUT = "/logout";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
//            .csrf(AbstractHttpConfigurer::disable)      // csrf disable 처리
            .csrf(cs -> cs.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
            .cors(corsConfigurer -> corsConfigurer.configurationSource(request -> corsConfiguration()))
            .headers(header ->
                       header
                           .xssProtection(Customizer.withDefaults())
                           .referrerPolicy(policyConfig -> policyConfig.policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.SAME_ORIGIN))
            )
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
                    .successHandler(successHandler)
                    .failureHandler(failureHandler)
                    .usernameParameter("userId")
                    .loginProcessingUrl("/loginProc")
            )
            .oauth2Login( httpSecurityOAuth2LoginConfigurer ->
                    httpSecurityOAuth2LoginConfigurer
                            .loginPage(LOGIN)
                            .successHandler(successHandler)
                            .failureHandler(failureHandler)
                            .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userService(principalOAuth2UserService))
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

    private CorsConfiguration corsConfiguration(){
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowedOriginPatterns(List.of("*"));       // 모든 출처 허용
        cors.setAllowedMethods(List.of("*"));       // 모든 http 메서드 허용
        cors.setAllowedHeaders(List.of("*"));       // 모든 헤더 허용
        cors.setAllowCredentials(true);                 // 자격 증명 허용 => 웹 브라우저가 요청을 보낼 때 쿠키와 HTTP 인증 정보를 함께 보내는지 여부를 결정한다.
        cors.setMaxAge(3600L);                          // pre-flight 요청 캐시 시간 (초)
        return cors;
    }
}
