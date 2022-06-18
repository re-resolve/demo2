package com.example.demo2.vo;

import com.example.demo2.common.ResultCode;
import com.example.demo2.vo.ResponseResult;

public class ResponseResultFactory {
    /**
     * 构建应该通用的成功的返回结果
     * @return
     */
    public static ResponseResult buildResponseResult(){
        return new ResponseResult(ResultCode.SYSTEM_SUCCESS);
    }
    
    public static ResponseResult buildResponseResult(String resultCode){
        return new ResponseResult(resultCode);
    }
    
    public static ResponseResult buildResponseResult(String resultCode,String resultMsg){
        return new ResponseResult(resultCode,resultMsg);
    }
    public static <T> ResponseResult buildResponseResult(String resultCode, String resultMsg, T obj){
        return new ResponseResult<T>(resultCode,resultMsg,obj);
    }
}
