package com.example.demo2.controller;

import com.example.demo2.dto.GetTokenIdBody;
import com.example.demo2.service.GetTokenIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokenid")
public class qawkudhqwiudd {
    @Autowired
    private GetTokenIdService getTokenIdService;
    
    @PostMapping()
    public String getTokenId(@RequestBody GetTokenIdBody getTokenIdBody){
        return getTokenIdService.getToken();
    }
}
