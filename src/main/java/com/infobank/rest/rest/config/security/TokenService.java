package com.infobank.rest.rest.config.security;

import java.util.Date;

import com.infobank.rest.rest.model.Usuario;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication) {
        Usuario principal = (Usuario) authentication.getPrincipal();
        Date date = new Date();
        Date dateExp = new Date(date.getTime() + Long.parseLong(expiration));

        return Jwts.builder().setIssuer("Forum API").setSubject(principal.getId().toString()).setIssuedAt(date)
                .setExpiration(dateExp).signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUser(String token) {
        String userId = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().getSubject();
        return Long.parseLong(userId);
    }
}
