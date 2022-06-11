package com.example.demo2.common;

public class ResponseResult<T> {
    public String getResultCode() {
        return resultCode;
    }
    
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
    
    public String getResultMsg() {
        return resultMsg;
    }
    
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
    
    public T getResult() {
        return result;
    }
    
    public void setResult(T result) {
        this.result = result;
    }
    
    //当前响应的状态码
    String resultCode;
    //当前响应的信息(错误时会写上)
    String resultMsg;
    //具体的结果
    T result;
    
    
}
