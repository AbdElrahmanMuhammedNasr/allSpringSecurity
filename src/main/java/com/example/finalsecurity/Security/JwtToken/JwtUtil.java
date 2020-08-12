package com.example.finalsecurity.Security.JwtToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    String SECRET_KET ="my Sec";
    private static Authentication authentication = null;

    public String generateToken(){
        authentication =  SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub",authentication.getName());
        claims.put("created", new Date());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 10000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KET)
                .compact();
    }

    public  boolean isvalid(String token){
        if(getUserName(token) .equals(authentication.getName()) && !isTokenExpired(token)){
            return true;
        }
        return false;
    }

    private String getUserName(String token){
        return Jwts.parser().setSigningKey(SECRET_KET).parseClaimsJwt(token).getBody().getSubject();
    }
    private boolean isTokenExpired(String token){
        return  Jwts.parser().setSigningKey(SECRET_KET).parseClaimsJwt(token).getBody().getExpiration() .before(new Date(System.currentTimeMillis()));
    }

}
