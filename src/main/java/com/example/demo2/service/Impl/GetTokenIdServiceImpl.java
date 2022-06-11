package com.example.demo2.service.Impl;

import com.example.demo2.common.GetSetToken;
import com.example.demo2.dto.Token;
import com.example.demo2.service.GetTokenIdService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

@Service
public class GetTokenIdServiceImpl implements GetTokenIdService {
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

        String result = HttpPost.doPostToJson(url,re.toString());
        Gson gson = new Gson();
        Token token = gson.fromJson(result, Token.class);
        //System.out.println(token.getData().getToken_id());
        String tokenid=token.getData().getToken_id();
        int i=1;
        if(tokenid.isBlank()&&i==1){
            String result1 = HttpPost.doPostToJson(url,re.toString());
            Gson gson1 = new Gson();
            Token token1 = gson.fromJson(result1, Token.class);
            i++;
        }
        GetSetToken.setToken(tokenid);
        return tokenid;
    }
}
