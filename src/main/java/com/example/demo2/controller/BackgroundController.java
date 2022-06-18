package com.example.demo2.controller;

import com.example.demo2.common.*;
import com.example.demo2.dto.BackgroundLogin;
import com.example.demo2.mapper.BackgroundLoginMapper;
import com.example.demo2.vo.ResponseResult;
import com.example.demo2.vo.ResponseResultFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/background")
public class BackgroundController {
    @Resource
    BackgroundLoginMapper backgroundLoginMapper;
    
    //查询账号是否存在
    public Boolean search(String name){
        if(backgroundLoginMapper.search(name).equals(1)){
            return true;
        }
        else{
            return false;
        }
    }
    
    @PostMapping("/logon")//后台账号注册
    public ResponseResult Logon(@RequestBody BackgroundLogin backgroundLogin){
        if(search(backgroundLogin.getName())){
            ResponseResult responseResult= ResponseResultFactory.buildResponseResult(ResultCode.BACKGROUND_LOGON_ACCOUNT_ERROR,"wrong pwd");
    
            return  responseResult;
        }
        else if(backgroundLogin.getPwd().length()<6|| backgroundLogin.getPwd().length()>14){
            ResponseResult responseResult= ResponseResultFactory.buildResponseResult(ResultCode.BACKGROUND_LOGON_PWD_ERROR,"wrong pwd");
    
            return  responseResult;
        }
        else{
            //将用户名字添加进用户列表中，校验token
            UserList.user.add(backgroundLogin.getName());
            
            backgroundLoginMapper.create(backgroundLogin);
            ResponseResult responseResult= ResponseResultFactory.buildResponseResult(ResultCode.BACKGROUND_LOGON_SUCCESS,"created successfully");
    
            return  responseResult;
         
        }
    }
    
    
    @PostMapping("/login")//后台账号登录
    public ResponseResult Login(@RequestBody BackgroundLogin backgroundLogin){
        if(backgroundLoginMapper.login(backgroundLogin).equals(true)){
            //生成一个token
            CreateToken createToken = new CreateToken();
            String token= createToken.jwt(backgroundLogin.getName());
            
            ResponseResult<String> responseResult= ResponseResultFactory.buildResponseResult(ResultCode.BACKGROUND_LOGIN_SUCCESS,"login successfully",token);
            
            return  responseResult;
//            return "login successfully";
        }
        else{
            ResponseResult responseResult= ResponseResultFactory.buildResponseResult(ResultCode.BACKGROUND_LOGIN_FAIL,"login failed");
    
            return  responseResult;
//            return "login failed";
        }
    }
    
    @DeleteMapping("/delete")//删除后台帐号
    public ResponseResult delete(@RequestBody BackgroundLogin backgroundLogin){
        String name=new Verify().parser(backgroundLogin.getToken(),new CreateToken().getSignature());
        if(UserList.user.contains(name)){
            if(backgroundLoginMapper.delete(backgroundLogin.getName()).equals(true)){
        
                ResponseResult responseResult= ResponseResultFactory.buildResponseResult(ResultCode.BACKGROUND_DELETE_SUCCESS,"delete successfully");
            //删除用户列表中的该用户
                UserList.user.remove(name);
                return  responseResult;
            }
            else{
                ResponseResult responseResult= ResponseResultFactory.buildResponseResult(ResultCode.BACKGROUND_DELETE_FAIL,"delete successfully");
        
                return  responseResult;
            }
        }
        else{
            ResponseResult responseResult=ResponseResultFactory.buildResponseResult(ResultCode.SYSTEM_ERROR_TOKEN,"invalid token");
            return responseResult;
        }
        
    }
}
