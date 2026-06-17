package com.mock.ratelimiter.Interface.Impl;

import com.mock.ratelimiter.Interface.RateLimitStrategy;

public class FixedWindowStrategy implements RateLimitStrategy{
    // usually read from the properties files..
    long lastWindowTime;
    long windowLength;
    int maxRequestPerWindow;
    int currentCountInThisWindow;
    public FixedWindowStrategy(){
        lastWindowTime = 0L;
        maxRequestPerWindow = 1000;
        windowLength = 1000;
        currentCountInThisWindow = 0;

    }
    public boolean rateLimit() throws Exception{
        // this thing is that.. it will have the time intervals.. after which , it will create a new window and allow requests..
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastWindowTime > windowLength){
            lastWindowTime = lastWindowTime + windowLength;
            currentCountInThisWindow++;
        }else if(currentCountInThisWindow > maxRequestPerWindow){
            throw new Exception(" Too many requests ");
        }else{
            currentCountInThisWindow++;
        }
        return true;
    }


}
