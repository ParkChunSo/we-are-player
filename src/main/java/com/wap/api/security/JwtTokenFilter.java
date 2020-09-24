package com.wap.api.security;

import com.wap.api.error.exception.AccessDeniedAuthenticationException;
import com.wap.api.security.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws AccessDeniedAuthenticationException,ServletException, IOException {

        final Optional<String> token = jwtTokenProvider.resolveToken(request);
        if (token.isPresent() && jwtTokenProvider.validateToken(token.get())) {
            Authentication auth = jwtTokenProvider.getAuthentication(token.get());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }
}
