package com.adryel0713.backend.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private final static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final static Long EXPIRATION_TIME = 864000000L; // 1 dia

    public static String gerarToken(String username){
        return Jwts.builder()
                .signWith(key)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .setSubject(username)
                .compact();
    }
    public static String extrairUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public static boolean validarToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }
        catch (JwtException j){
            return false;
        }
    }


}
