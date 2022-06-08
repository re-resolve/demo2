package com.example.demo2.service.Impl;

import com.example.demo2.dto.Huawei;
import com.example.demo2.service.HuaweiHttpService;
import org.springframework.stereotype.Service;

@Service
public class HuaweiHttpServiceImpl implements HuaweiHttpService {
    
    @Override
    public String doHuaweiHttp(Huawei huawei) {
        String method = huawei.getMethod().toUpperCase();
        String res = "";
        if (method.equals("GET") ){
            res = HttpGet.doGet(huawei.getUrl());
        }else{
            res = HttpPostPutDelete.doPostToJson(huawei.getUrl(),method,huawei.getData());
        }
        return res;
    }
}
