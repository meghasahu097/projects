package com.example.UserAuthentication.service;

import java.util.Date;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Component

public class JwtTokenProvider {
	
	private static final long EXPIRATION_TIME = 3600000; // 1 hour in milliseconds
	private static final String SECRET_KEY="adminw4545jhdsbd67t6646564545adminw4545jhdsbd67t6646564545adminw4545jhdsbd67t6646564545";
	
	public static String generateToken(String username){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
    
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // Invalid signature
        	System.out.println(e);
            return false;
        }
    }
    
    public static String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
