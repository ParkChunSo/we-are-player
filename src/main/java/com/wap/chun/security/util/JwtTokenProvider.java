package com.wap.chun.security.util;

import com.wap.chun.domain.enums.MemberRole;
import com.wap.chun.security.error.InvalidJwtAuthenticationException;
import com.wap.chun.profile.member.service.MemberService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final MemberService memberService;
    private final String secretKey = Base64.getEncoder().encodeToString("secret".getBytes());
    private final long validityInMilliseconds = 3600000L;
    public String createToken(String username, Set<MemberRole> roles) {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
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
            if (String.valueOf(o).equals("CLIENT")) {
                roles.add(new SimpleGrantedAuthority("ROLE_" + MemberRole.CLIENT.toString()));
            } else {
                roles.add(new SimpleGrantedAuthority("ROLE_" + MemberRole.ADMIN.toString()));
            }
        }
        return roles;
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        }
    }
}
