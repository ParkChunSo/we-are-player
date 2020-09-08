package com.chun.apps.match.config;

import com.chun.modules.security.JwtTokenProvider;
import com.wap.cores.JWTSecretKeySource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtTokenConfig {
    @Bean
    public JwtTokenProvider jwtTokenProvider(){
        return new JwtTokenProvider(JWTSecretKeySource.readSecretKey());
    }
}
