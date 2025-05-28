package com.paccy.springbootne2025.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    public <T>  T extractClaim(String token, Function <Claims,T> claimResolver){

        Claims claims= extractAllClaims(token);
        return  claimResolver.apply(claims);
    }

    public Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    public SecretKey getSecretKey(){
        byte[] keyBytes= Base64.getDecoder().decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Date getExpirationTime(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    public boolean isTokenExpired(String token){
        return getExpirationTime(token).before(new Date());
    }

    public boolean isTokenValid (String token,UserDetails user){
        String username= extractUsername(token);
        return  (user.getUsername().equals(username) && !isTokenExpired(token));
    }

    public String extractUsername(String token){
        return extractClaim(token,Claims::getSubject);
    }

    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(userDetails.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 2 * 24 * 60 * 60 *1000))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
