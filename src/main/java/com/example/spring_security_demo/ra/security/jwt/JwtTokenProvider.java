package com.example.spring_security_demo.ra.security.jwt;

import com.example.spring_security_demo.ra.security.userPrincipal.CustomUserDetail;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
@Value("${ra.jwt.secret}")
    private String JWT_SECRET;
@Value("${ra.jwt.expiration}")
    private int JWT_EXPIRATION;
public String createToken(CustomUserDetail customUserDetail){
    Date now = new Date();
    Date dateExpired = new Date(now.getTime() + JWT_EXPIRATION);
    return Jwts.builder().setSubject(customUserDetail.getUsername())
            .setIssuedAt(now)
            .setExpiration(dateExpired)
            .signWith(SignatureAlgorithm.ES256,JWT_SECRET)
            .compact();
}
public String getUserNameFromJwtToken(String token){
    return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getSubject();
}
public boolean validateToken(String token){
    try {
        Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
        return true;
    } catch (ExpiredJwtException e){
log.error("Invalid JWT Token");
    } catch (UnsupportedJwtException e){
        log.error("Expired JWT Token");
    } catch (MalformedJwtException e){
        log.error("Unsupported JWT Token");
    }catch (IllegalArgumentException ex){
        log.error("JWT claims String is empty");
    }
    return false;
}
}
