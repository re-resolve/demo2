package com.example.demo2.controller;


import com.example.demo2.dto.HuaweiGetToken;

import com.example.demo2.service.HuaweiHttpService;

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
    public String dohttp(@RequestBody HuaweiGetToken huawei){
        //日志输出
        final Logger logger= LoggerFactory.getLogger(HuaweiApiController.class);
        if(huawei.getUrl().isBlank()){
            logger.error("HuaweiApi url is NULLorEMPTY");
            logger.info("param:  "+huawei);
        }
    
        String res = huaweiHttpService.doHuaweiHttp(huawei);
        
        System.out.println("111"+res);
        return res;
    }
}
