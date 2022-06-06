package com.example.demo2.service.Impl;

import com.example.demo2.dto.GetTokenIdBody;
import com.example.demo2.dto.Token;
import com.example.demo2.service.GetTokenIdService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.huawei.cloudcampus.api.ApiClient;
import com.huawei.cloudcampus.api.ApiException;
import com.huawei.cloudcampus.api.JSON;
import org.springframework.stereotype.Service;

@Service
public class GetTokenIdServiceImpl implements GetTokenIdService {

//    @Override
//    public String getTokenId(GetTokenIdBody getTokenIdBody){
//        String tenantName = getTokenIdBody.getUserName();
//        String tenantPwd = getTokenIdBody.getPassword();
//        String host = "cn2.naas.huaweicloud.com";
//        String port = "18002";
//        //String url=getTokenIdBody.getUrl();
//        ApiClient apiClient = new ApiClient();
//        apiClient.setTenantPwd(tenantPwd);
//        apiClient.setTenantName(tenantName);
//        apiClient.setHost(host);
//        apiClient.setPort(port);
//
//        return apiClient.getTokenId();
//    }
    
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
    
    
        return token.getData().getToken_id();
    }
}
