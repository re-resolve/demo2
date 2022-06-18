package com.example.demo2.vo;

import lombok.Data;

@Data
public class ResponseResult<T> {
    
    
    public ResponseResult(String resultCode, String resultMsg, T result) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.result = result;
    }
    public ResponseResult(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }
    public ResponseResult(String resultCode) {
        this.resultCode = resultCode;
    }
    //当前响应的状态码
    String resultCode;
    //当前响应的信息(错误时会写上)
    String resultMsg;
    //具体的结果
    T result;
    
    
}
