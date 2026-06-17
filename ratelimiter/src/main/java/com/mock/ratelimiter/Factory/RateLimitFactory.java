package main.java.com.mock.ratelimiter.Factory;

import main.java.com.mock.ratelimiter.Interface.RateLimitStrategy;
import main.java.com.mock.ratelimiter.Interface.Impl.LeakyBucketStrategy;
import main.java.com.mock.ratelimiter.Interface.Impl.TokenBucketStrategy;

public class RateLimitFactory {
    private RateLimitFactory(){

    }
    public RateLimitStrategy getInstance(RateLimitType rateLimitType){
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
