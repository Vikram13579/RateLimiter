package com.mock.ratelimiter.Service;

import com.mock.ratelimiter.Factory.RateLimitFactory;
import com.mock.ratelimiter.Interface.RateLimitStrategy;
import com.mock.ratelimiter.enums.RateLimitType;
public class RateLimiterService {
    RateLimitStrategy strategy;
    RateLimitFactory factory;
  
    public boolean rateLimit(RateLimitType rateLimitType) throws Exception{
        // so here..
        // methods to ratelimit.. fixed window counter..
        // sliding window algorithm
        // leaky bucket
        // token bucket algorithms..
        strategy = RateLimitFactory.getInstance(rateLimitType);
        return strategy.rateLimit();

    }
}
