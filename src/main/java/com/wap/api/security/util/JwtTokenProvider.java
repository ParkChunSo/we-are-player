package com.wap.api.security.util;

import com.wap.api.domain.enums.MemberRole;
import com.wap.api.error.ErrorCode;
import com.wap.api.error.exception.InvalidJwtAuthenticationException;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Component
public class JwtTokenProvider {
    private final String secretKey = Base64.getEncoder().encodeToString("secret".getBytes());

    public String createToken(String username, Set<MemberRole> roles) {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.UTC))) //한시간
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
    }

    public Authentication getAuthentication(String token) {
        //DB에 접근하지 않고 JWT 파싱해서 사용
        UserDetails userDetails = User.builder()
                .username(getUsername(token))
                .password("") //UserDetails를 사용해야하기 떄문에 어쩔 수 없이 사용.
                .authorities(getUserRoles(token))
                .build();

        return new UsernamePasswordAuthenticationToken(userDetails, "", getUserRoles(token));
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Collection<SimpleGrantedAuthority> getUserRoles(String token) {
        List list = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("roles", List.class);

        Collection<SimpleGrantedAuthority> roles = new HashSet<>();
        for (Object o : list) {
            roles.add(new SimpleGrantedAuthority("ROLE_" + o));
        }

        return roles;
    }

    public boolean hasRole(String token, MemberRole role) {
        Set list = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("roles", Set.class);

        return list.contains(role);
    }

    public Optional<String> resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return Optional.of(bearerToken.substring(7));
        }

        return Optional.empty();
    }

    public String resolveToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }

        return "";
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException e) {
            throw new InvalidJwtAuthenticationException(ErrorCode.EXPIRED_TOKEN);
        } catch (IllegalArgumentException e) {
            throw new InvalidJwtAuthenticationException(ErrorCode.INVALID_TOKEN);
        }
    }
}
