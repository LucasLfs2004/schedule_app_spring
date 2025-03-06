package com.scheduling.scheduling_app.service;

import com.scheduling.scheduling_app.dto.UserDto;
import com.scheduling.scheduling_app.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    private final String SECRET_KEY = "09d25e094faa6ca2556c818166b7a9563b93f7099f6f0f4caa6cf63b88e8d3e8";

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("id", user.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 100))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractSubject(String token) {
        return
    }

    public Claims extrectAllClaims(String token) {
        return Jwts.parser()
                .setSigningKeyResolver(SECRET_KEY)
                .parseClaimsJws(token);
    }
}
