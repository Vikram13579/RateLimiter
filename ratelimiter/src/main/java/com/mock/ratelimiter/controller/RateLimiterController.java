package com.mock.ratelimiter.controller;

import com.mock.ratelimiter.Service.RateLimiterService;
import com.mock.ratelimiter.enums.RateLimitType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mock.ratelimiter.Entity.Status;
import  com.mock.ratelimiter.Interface.RateLimitStrategy;

@RestController // this tells spring.. to convert the reponse into the serialize 
@RequestMapping("/api/ratelimiter")
public class RateLimiterController {
    private RateLimiterService rateLimiter; 
    @GetMapping("/{id}")
    public Status rateLimitMock(@PathVariable Long id){
        return new Status(id ," allowed");
    }
    
    @GetMapping("/ping")// this one takes no input and it just return whatever the rateLimit function
    public String rateLimit() throws Exception{
        if(rateLimiter.rateLimit(RateLimitType.TOKEN_BUCKET)){
            return "  This one was succcess ";
        }
        return " Too many requests ";
    }

    // @GetMapping("/input")
    // public String rateLimitWithReq(@RequestBody DummyReq dto){
    //     rateLimit.dummyHandler();
    //     return "empty";
    // }
}
