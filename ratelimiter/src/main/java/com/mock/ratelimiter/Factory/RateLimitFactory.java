package com.mock.ratelimiter.Factory;

import com.mock.ratelimiter.Interface.RateLimitStrategy;
import com.mock.ratelimiter.Interface.Impl.FixedWindowStrategy;
import com.mock.ratelimiter.Interface.Impl.LeakyBucketStrategy;
import com.mock.ratelimiter.Interface.Impl.SlidingWindowStrategy;
import com.mock.ratelimiter.Interface.Impl.TokenBucketStrategy;
import com.mock.ratelimiter.enums.RateLimitType;



public class RateLimitFactory {
    private RateLimitFactory(){

    }
    public static RateLimitStrategy getInstance(RateLimitType rateLimitType){
        if(rateLimitType == RateLimitType.TOKEN_BUCKET){
            return new TokenBucketStrategy();
        }else if(rateLimitType == RateLimitType.LEAKY_BUCKET){
            return new LeakyBucketStrategy();
        }else if(rateLimitType == RateLimitType.SLIDING_WINDOW){
            return new SlidingWindowStrategy();
        }
        else{
            return new FixedWindowStrategy();
        }
    }
}
