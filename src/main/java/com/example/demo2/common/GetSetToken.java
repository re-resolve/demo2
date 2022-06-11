package com.example.demo2.common;

public class GetSetToken {
    private static String token;
    
    public static String getToken() {
        return token;
    }
    
    public static void setToken(String token) {
        GetSetToken.token = token;
    }
}
