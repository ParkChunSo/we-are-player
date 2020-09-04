package com.chun.apps.member.config;

import com.chun.commons.enums.MemberRole;
import com.chun.modules.security.JwtTokenFilter;
import com.chun.modules.security.JwtTokenProvider;
import com.chun.modules.security.UserDetailsImpl;
import com.wap.cores.JWTSecretKeySource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()

                .antMatchers(HttpMethod.POST, "/member/login").permitAll()
                .antMatchers(HttpMethod.POST, "/member").permitAll()
                .antMatchers(HttpMethod.POST, "/member/admin").hasRole(MemberRole.ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/member/all").hasRole(MemberRole.ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/member/**").permitAll()
                .antMatchers(HttpMethod.POST, "/member/**").authenticated()
                .anyRequest().anonymous()

                .and()
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider()), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsImpl())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtTokenProvider jwtTokenProvider(){
        return new JwtTokenProvider(JWTSecretKeySource.readSecretKey());
    }
}
