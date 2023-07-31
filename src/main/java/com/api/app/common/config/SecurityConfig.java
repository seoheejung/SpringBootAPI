package com.api.app.common.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                // CORS 구성 설정
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                // 모든 HTTP 요청에 대해 권한 설정
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest()
                        .permitAll() // 전체 허용
                )
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    // CORS 구성 생성
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        // 모든 원본(Origin)을 허용하도록 설정
        config.addAllowedOriginPattern("*");
        // 크로스 도메인 요청에 인증 정보를 포함 허용하도록 설정
        config.setAllowCredentials(true);
        // 허용할 HTTP 메서드를 설정
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        // 모든 요청 헤더를 허용하도록 설정
        config.setAllowedHeaders(List.of("*"));
        // 노출할 응답 헤더를 설정 (모든 헤더 노출)
        config.setExposedHeaders(List.of("*"));
        // URL 기반의 CORS 구성 소스를 생성
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 모든 경로(/**)에 대한 CORS 구성을 등록
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
