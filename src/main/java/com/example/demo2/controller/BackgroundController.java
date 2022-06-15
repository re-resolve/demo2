package com.example.demo2.controller;

import com.example.demo2.entity.BackgroundLogin;
import com.example.demo2.mapper.BackgroundLoginMapper;
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
    public String Logon(@RequestBody BackgroundLogin backgroundLogin){
        if(search(backgroundLogin.getName())){
            return "account is existed";
        }
        else if(backgroundLogin.getPwd().length()<6||backgroundLogin.getPwd().length()>14){
            return "wrong pwd";
        }
        else{
            backgroundLoginMapper.create(backgroundLogin);
            return "created successfully";
        }
    }
    
    
    @PostMapping("/login")//后台账号登录
    public String Login(@RequestBody BackgroundLogin backgroundLogin){
        if(backgroundLoginMapper.login(backgroundLogin).equals(true)){
            return "login successfully";
        }
        else{
            return "login failed";
        }
    }
    
    @DeleteMapping("/{name}")
    public String delete(@PathVariable("name") String name){
        if(backgroundLoginMapper.delete(name).equals(true)){
            return "delete successfully";
        }
        else{
            return "delete failed";
        }
    }
}
