package com.example.demo2.dto;

public class Token {
  
    public class Data {
        private String token_id;
        private String expiredDate;
        public String getToken_id() {
            return token_id;
        }
        public String getExpiredDate() {
            return expiredDate;
        }
    }
    private String errcode;
    private String errmsg;
    public String getErrcode() {
        return errcode;
    }
    
    public String getErrmsg() {
        return errmsg;
    }
    
    private Data data;
    public Data getData() {
        return data;
    }
}

