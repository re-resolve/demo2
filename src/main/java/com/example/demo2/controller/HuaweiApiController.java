package com.example.demo2.controller;


import com.example.demo2.common.*;
import com.example.demo2.dto.HuaweiGetToken;

import com.example.demo2.service.HuaweiHttpService;

import com.example.demo2.vo.ResponseResult;
import com.example.demo2.vo.ResponseResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/huaweiapi")
public class HuaweiApiController {
    @Autowired
    private HuaweiHttpService huaweiHttpService;
    
    @PostMapping()
    public ResponseResult dohttp(@RequestBody HuaweiGetToken huawei){
        //校验token
        String name=new Verify().parser(huawei.getToken(), new CreateToken().getSignature());
        if(UserList.user.contains(name))
        {
            huawei.setUrl("https://cn2.naas.huaweicloud.com:18002"+huawei.getUrl());
            //日志输出
            final Logger logger= LoggerFactory.getLogger(HuaweiApiController.class);
            if(huawei.getUrl().isBlank()){
                logger.error("HuaweiApi url is NULLorEMPTY");
                logger.info("param:  "+huawei);
            }
    
            String res = huaweiHttpService.doHuaweiHttp(huawei);
            ResponseResult<String> responseResult= ResponseResultFactory.buildResponseResult(ResultCode.SYSTEM_SUCCESS_TOKEN,"dohttp successfully",res);
            System.out.println("111"+res);
            return responseResult;
        }
        else{
            ResponseResult responseResult=ResponseResultFactory.buildResponseResult(ResultCode.SYSTEM_ERROR_TOKEN,"invalid token");
            return responseResult;
        }
        
    }
}
