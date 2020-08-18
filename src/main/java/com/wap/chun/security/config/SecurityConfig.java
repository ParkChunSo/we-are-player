package com.wap.chun.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.wap.chun.domain.enums.MemberRole;
import com.wap.chun.security.JwtTokenFilter;
import com.wap.chun.security.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/member/login").permitAll()
                    .antMatchers(HttpMethod.POST, "/member/signUp/member").permitAll()
                    .antMatchers(HttpMethod.POST, "/member/signUp/admin").hasRole(MemberRole.ADMIN.toString())
                    .antMatchers(HttpMethod.GET, "/member/all").hasRole(MemberRole.ADMIN.toString())
                    .antMatchers("/member/**").authenticated()

                    .antMatchers(HttpMethod.GET, "/club/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/club/**").authenticated()

                    .antMatchers(HttpMethod.GET,"/match/**").permitAll()
                    .antMatchers(HttpMethod.POST,"/match/**").authenticated()
                    .antMatchers(HttpMethod.PUT,"/match/**").authenticated()

                    //TODO("추가적인 URL 고려 및 ENUM으로 뺴는거 생각해보기")
                    .anyRequest().anonymous()

                .and()
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}


