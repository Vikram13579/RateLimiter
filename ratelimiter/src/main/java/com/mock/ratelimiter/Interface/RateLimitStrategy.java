package com.mock.ratelimiter.Interface;

public interface RateLimitStrategy{
    public boolean rateLimit()  throws Exception;
}
