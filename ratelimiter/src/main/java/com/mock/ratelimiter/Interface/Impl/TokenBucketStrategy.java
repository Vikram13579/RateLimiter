package com.mock.ratelimiter.Interface.Impl;

import com.mock.ratelimiter.Interface.RateLimitStrategy;

public class TokenBucketStrategy  implements RateLimitStrategy{
    // so for the tokenbucket the variables will be tokens kept getting added into the bucket and we consume it..

    int tokenCount ;
    int maxTokenHeld;
    long lastTokenUpdatedTime;
    int tokensPerSecond;
    int requestCount = 0;
    public TokenBucketStrategy(){
        tokenCount = 0;
        requestCount = 0;
        lastTokenUpdatedTime = 0;
        tokensPerSecond = 3;
        maxTokenHeld = 10;
    }
    public boolean rateLimit() throws Exception{ // add synchronized..
        long currentTime = System.currentTimeMillis();
        long elapsedTime = (currentTime - lastTokenUpdatedTime);
        int addedTokens = (int) (elapsedTime * tokensPerSecond /1000);
        tokenCount = Math.min(maxTokenHeld, tokenCount + addedTokens);
        lastTokenUpdatedTime += (long) addedTokens * 1000 / tokensPerSecond;
        if(tokenCount == 0){
            throw new Exception("Too many requests");
        }
        else{
            tokenCount--;
        }
        return true;
    }
}