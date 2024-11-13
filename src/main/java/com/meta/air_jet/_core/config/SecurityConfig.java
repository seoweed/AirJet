package com.meta.air_jet._core.config;

import com.meta.air_jet._core.filter.JwtRequestFilter;
import com.meta.air_jet._core.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(UserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    private static final String[] WHITE_LIST = {
            "/",
            "/api/map/*",
            "/api/test/*",
            "/api/test/s3/save",
            "/api/voice/save",
            "/api/upload",
            "/api/maps",
            "/api/voice",
            "/api/geocode",
            "/api/voice/test",
            "api/image/*",
            "/api/getImage",
            "/api/image/save",
            "/api/map/create",
            "/api/auth/**",
            "/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**"
            ,"/h2-console/**"  // h2-console 경로 추가
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrfConfig) ->
                        csrfConfig.disable())
                // 세션 관리 방식을 STATELESS로 설정. 이 설정은 서버에서 세션을 저장하지 않고,
                // 클라이언트가 인증 정보를 매번 제공해야 함을 의미. 주로 JWT 기반 인증을 사용할 때 사용
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers(WHITE_LIST).permitAll()
                                .anyRequest().authenticated())
                .headers(headers -> headers
                        .frameOptions().disable()  // H2 콘솔에서 프레임 사용 허용
                )
//                .exceptionHandling((exception) ->
//                        {
//                            exception.authenticationEntryPoint(authenticationEntryPoint());
//                            exception.accessDeniedHandler(accessDeniedHandler());
//                        }
//                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
