package com.example.demo2.dto;

public class Token {
    /*private Object data{
    
    }*/
    
    public class Data {
        public String getToken_id() {
            return token_id;
        }
        
        public String getExpiredDate() {
            return expiredDate;
        }
        
        private String token_id;
        private String expiredDate;
        
    }
    
    private Data data;
    
    private String errcode;
    private String errmsg;
    
    public String getErrcode() {
        return errcode;
    }
    
    public String getErrmsg() {
        return errmsg;
    }
    
    public Data getData() {
        return data;
    }
}

