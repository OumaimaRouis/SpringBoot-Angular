package com.gestion3.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class JwtService {
    @Value("${spring.app.expiration}")
    long expiration;
    @Value("${spring.app.secretkey}")
    public String secretkey;
    public String getTokenFromHeader(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token !=null && token.startsWith("Bearer "))
            return token.substring(7);
        return null;
    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities().stream().map(x->x.getAuthority()).toList());
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+expiration*60*60))
                .addClaims(claims)
                .signWith(key())
                .compact();

    }


    public String getUserNameFromToken(String token)
    {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Date getExpirationNameFromToken(String token)
    {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }
    public boolean validate(UserDetails userDetails, String token)
    {
        return userDetails.getUsername().equals(getUserNameFromToken(token))
                &&
                new Date().before(getExpirationNameFromToken(token));
    }
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretkey));
    }
}