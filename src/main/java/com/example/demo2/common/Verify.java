package com.example.demo2.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

public class Verify {
    /**
     *
     * @param token
     * @param signature
     * @return name 用户名字
     */
    public String parser(String token,String signature){
        JwtParser jwtParser= Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(signature).parseClaimsJws(token);
        Claims claims=claimsJws.getBody();
        return claims.get("name").toString();
    }
    
}
