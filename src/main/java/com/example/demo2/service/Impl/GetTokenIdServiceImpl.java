package com.example.demo2.service.Impl;

import com.example.demo2.common.GetSetToken;
import com.example.demo2.controller.HuaweiApiController;
import com.example.demo2.dto.Token;
import com.example.demo2.service.GetTokenIdService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetTokenIdServiceImpl implements GetTokenIdService {
    final Logger logger= LoggerFactory.getLogger(GetTokenIdServiceImpl.class);
    /**
     *
     * @return tokenid
     */
    @Override
    public String getToken(){
        String tenantName = "c4_usr_216";
        String tenantPwd = "1qaz@WSX_216";
        String url = "https://cn2.naas.huaweicloud.com:18002/controller/v2/tokens";

        JsonObject re = new JsonObject();
        re.addProperty("userName",tenantName);
        re.addProperty("password",tenantPwd);
        
        //发请求，获取华为的token
        String result = HttpPost.doPostToJson(url,re.toString());
        Gson gson = new Gson();
        Token token = gson.fromJson(result, Token.class);
        String tokenid=token.getData().getToken_id();
        //如果得到了空的token就再发一次请求
        int i=1;
        String tokenid1=null;
        if(tokenid.isBlank()&&i==1){
            String result1 = HttpPost.doPostToJson(url,re.toString());
            Gson gson1 = new Gson();
            Token token1 = gson1.fromJson(result1, Token.class);
            tokenid1=token.getData().getToken_id();
            i++;
        }
        if(i==1){
            GetSetToken.setToken(tokenid);
            logger.info("getHuaweiToken success");
        }
        else{
            GetSetToken.setToken(tokenid1);
            if(tokenid1.isBlank()){
                logger.error("getHuaweiToken failed");
            }
            else {
                logger.info("getHuaweiToken success");
            }
        }
        return tokenid;
    }
}
