package com.example.demo2.controller;


import com.example.demo2.common.*;
import com.example.demo2.dto.HuaweiGetToken;

import com.example.demo2.mapper.BackgroundLoginMapper;
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

import javax.annotation.Resource;

@RestController
@RequestMapping("/huaweiapi")
public class HuaweiApiController {
    @Autowired
    private HuaweiHttpService huaweiHttpService;
    @Resource
    BackgroundLoginMapper backgroundLoginMapper;
    
    /**
     * 判断name是否存在数据表中
     * @param name
     * @return
     */
    public Boolean search(String name){
        if(backgroundLoginMapper.search(name).equals(1)){
            return true;
        }
        else{
            return false;
        }
    }
    @PostMapping()
    public ResponseResult dohttp(@RequestBody HuaweiGetToken huawei){
        //日志输出
        final Logger logger= LoggerFactory.getLogger(HuaweiApiController.class);
    
        if(huawei.getToken().isBlank()){
            
            ResponseResult responseResult= ResponseResultFactory.buildResponseResult(ResultCode.SYSTEM_ERROR_TOKEN,"token is null");
            return responseResult;
        }
        else{
            //校验token
            String name=new Verify().parser(huawei.getToken(), new CreateToken().getSignature());
            if(search(name))
            {
                if(huawei.getUrl().isBlank()){
                    logger.error("HuaweiApi url is NULLorEMPTY");
                    logger.info("param:  "+huawei);
                }
                huawei.setUrl("https://cn2.naas.huaweicloud.com:18002"+huawei.getUrl());
        
                String res = huaweiHttpService.doHuaweiHttp(huawei);
                ResponseResult<String> responseResult= ResponseResultFactory.buildResponseResult(ResultCode.SYSTEM_SUCCESS_TOKEN,"dohttp successfully",res);
                logger.info("doHuaweiHttp success");
                //System.out.println("111"+res);
                return responseResult;
            }
            else{
                logger.error("invalid token");
                ResponseResult responseResult=ResponseResultFactory.buildResponseResult(ResultCode.SYSTEM_ERROR_TOKEN,"invalid token");
                return responseResult;
            }
        }
        
        
    }
}
