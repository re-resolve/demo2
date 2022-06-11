package com.example.demo2.service.Impl;

import com.example.demo2.dto.HuaweiGetToken;
import com.example.demo2.service.HuaweiHttpService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class HuaweiHttpServiceImpl implements HuaweiHttpService {
    
    @Override
    public String doHuaweiHttp(HuaweiGetToken huawei) {
        String method = huawei.getMethod().toUpperCase();
        String res = "";
        if (method.equals("GET") ){
            res = HttpGet.doGet(huawei.getUrl());
            if(Objects.equals(HttpGet.getResponseCode(), "401")){
                res = HttpGet.doGet(huawei.getUrl());
            }
        }
        else{
            res = HttpPostPutDelete.doPostToJson(huawei.getUrl(),method,huawei.getData());
            if(HttpPostPutDelete.getResponseCode().equals("401")){
                res = HttpPostPutDelete.doPostToJson(huawei.getUrl(),method,huawei.getData());
            }
        }
        return res;
    }
    
    //public void
}
