package com.example.demo2.controller;


import com.example.demo2.dto.Huawei;

import com.example.demo2.service.HuaweiHttpService;

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
    public String getTokenId(@RequestBody Huawei huawei){
        //System.out.println(huawei);
        String res = huaweiHttpService.doHuaweiHttp(huawei);
        //System.out.println("111"+res);
        return res;
    }
}
