package main.java.com.mock.ratelimiter.controller;

import main.java.com.mock.ratelimiter.Service.RateLimiterService;

@RestController // this tells spring.. to convert the reponse into the serialize 
@RequestMapping("/api/ratelimiter")
public class RateLimiterController {
    private RateLimiterService rateLimiter; 
    @GetMapping("/{id}")
    public Status rateLimitMock(@PathVariable Long id){
        return new Status(id ," allowed");
    }
    
    @GetMapping("/ping")// this one takes no input and it just return whatever the rateLimit function
    public String rateLimit(){
        if(rateLimiter.rateLimit()){
            return "  This one was succcess ";
        }
        return " Too many requests ";
    }

    @GetMapping("/input")
    public String rateLimitWithReq(@RequestBody DummyReq dto){
        rateLimit.dummyHandler();
        return "empty";
    }
}
