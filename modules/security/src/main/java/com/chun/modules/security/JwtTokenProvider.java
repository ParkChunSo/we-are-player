package com.chun.modules.security;

import com.chun.commons.enums.MemberRole;
import com.chun.commons.errors.ErrorCode;
import com.chun.commons.errors.exception.InvalidJwtAuthenticationException;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

public class JwtTokenProvider {
    private final String secretKey;

    public JwtTokenProvider(String secretKey){
        this.secretKey = encodeSecretKey(secretKey);
    }

    private String encodeSecretKey(String secretKey){
        return Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

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
        List list = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("roles", List.class);

        for (Object o : list) {
            if(o.toString().equals(role.name()))
                return true;
        }
        return false;
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
