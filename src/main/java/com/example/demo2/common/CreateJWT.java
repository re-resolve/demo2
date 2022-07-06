package com.example.demo2.common;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

public class CreateJWT {
    private long time=1000*60*60*24;//单位是毫秒
    
    private final String signature="secret";
    
    public String getSignature() {
        return signature;
    }
    
    public String jwt(String name){
        JwtBuilder jwtBuilder= Jwts.builder();
        String jwtToken=jwtBuilder
                //header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload
                .claim("name",name)
                .claim("role","admin")
                .setSubject("contact")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256,signature)
                //拼接
                .compact();
        return jwtToken;
    }
}
