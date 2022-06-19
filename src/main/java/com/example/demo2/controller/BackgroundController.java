package com.example.demo2.controller;

import com.example.demo2.common.*;
import com.example.demo2.dto.Background;
import com.example.demo2.dto.BackgroundLogin;
import com.example.demo2.mapper.BackgroundLoginMapper;
import com.example.demo2.vo.ResponseResult;
import com.example.demo2.vo.ResponseResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/background")
public class BackgroundController {
    final Logger logger = LoggerFactory.getLogger(BackgroundController.class);
    
    @Resource
    BackgroundLoginMapper backgroundLoginMapper;
    
    //查询账号是否存在
    public Boolean search(String name){
        if(backgroundLoginMapper.search(name).equals(1)){
            logger.info(name+" is existed in DB");
            return true;
        }
        else{
            logger.error(name+" is not existed in DB");
            return false;
        }
    }
    
    @PostMapping("/logon")//后台账号注册
    public ResponseResult Logon(@RequestBody BackgroundLogin backgroundLogin){
        logger.info("param: "+backgroundLogin);
        if(search(backgroundLogin.getName())){
            ResponseResult responseResult= ResponseResultFactory.buildResponseResult(ResultCode.BACKGROUND_LOGON_ACCOUNT_ERROR,"account is existed");
            
            logger.error("account is existed");
            return  responseResult;
        }
        else if(backgroundLogin.getPwd().length()<6|| backgroundLogin.getPwd().length()>14){
            ResponseResult responseResult= ResponseResultFactory.buildResponseResult(ResultCode.BACKGROUND_LOGON_PWD_ERROR,"wrong pwd");
            
            logger.error("wrong pwd_length");
            return  responseResult;
        }
        else{
            //md5加密
            String md5Password = DigestUtils.md5DigestAsHex(backgroundLogin.getPwd().getBytes());
            backgroundLoginMapper.create(backgroundLogin.getName(),md5Password);
            ResponseResult responseResult= ResponseResultFactory.buildResponseResult(ResultCode.BACKGROUND_LOGON_SUCCESS,"created successfully");
    
            logger.info("created a account successfully");
            return  responseResult;
         
        }
    }
    
    
    @PostMapping("/login")//后台账号登录
    public ResponseResult Login(@RequestBody BackgroundLogin backgroundLogin){
        logger.info("param: "+backgroundLogin);
    
        //md5加密
        String md5Password = DigestUtils.md5DigestAsHex(backgroundLogin.getPwd().getBytes());
        backgroundLoginMapper.create(backgroundLogin.getName(),md5Password);
        if(backgroundLoginMapper.login(backgroundLogin.getName(),md5Password).equals(true)){
            //生成一个token
            CreateToken createToken = new CreateToken();
            String token= createToken.jwt(backgroundLogin.getName());
            
            ResponseResult<String> responseResult= ResponseResultFactory.buildResponseResult(ResultCode.BACKGROUND_LOGIN_SUCCESS,"login successfully",token);
            logger.info("backgroundlogin : "+backgroundLogin.getName()+" success");
    
            return  responseResult;
//            return "login successfully";
        }
        else{
            ResponseResult responseResult= ResponseResultFactory.buildResponseResult(ResultCode.BACKGROUND_LOGIN_FAIL,"login failed");
            logger.error("backgroundlogin failed");
            return  responseResult;
//            return "login failed";
        }
    }
    
    @DeleteMapping("/delete")//删除后台帐号
    public ResponseResult delete(@RequestBody Background background){
        logger.info("param: "+background);
    
        if(background.getToken().isBlank()){
            ResponseResult responseResult= ResponseResultFactory.buildResponseResult(ResultCode.SYSTEM_ERROR_TOKEN,"token is null");
            logger.error("token is null");
            return responseResult;
        }
        else{
            String name=new Verify().parser(background.getToken(),new CreateToken().getSignature());
            if(search(name)){
                logger.info("verify name success");
                if(backgroundLoginMapper.delete(name).equals(true)){
            
                    ResponseResult responseResult= ResponseResultFactory.buildResponseResult(ResultCode.BACKGROUND_DELETE_SUCCESS,"delete "+name+" successfully");
                    //删除用户列表中的该用户
                    logger.info("delete "+name+" successfully");
                    return  responseResult;
                }
                else{
                    ResponseResult responseResult= ResponseResultFactory.buildResponseResult(ResultCode.BACKGROUND_DELETE_FAIL,"delete failed");
                    logger.error("delete failed");
                    return  responseResult;
                }
            }
            else{
                ResponseResult responseResult=ResponseResultFactory.buildResponseResult(ResultCode.SYSTEM_ERROR_TOKEN,"invalid token");
                logger.error("invalid token");
                return responseResult;
            }
        }
        
        
    }
}
