package com.mock.ratelimiter.Interface.Impl;

import com.mock.ratelimiter.Interface.RateLimitStrategy;

public class LeakyBucketStrategy  implements RateLimitStrategy{
    // so leaky bucket algorithm  leaks the requests.. every second.. so either a background process would be running which 
    // locks the list of requests.. and leak everything per second.. or like.. timer will be there and we will leak enough. everytime.. and see if it will be allowed or not..
    int leaksPerMilliSecond;
    int maxRequestsAllowedToHold ;
    long lastLeakedTime;
    int reqCount ;
    int leaksPerSecond;
    public LeakyBucketStrategy(){
        leaksPerSecond = 1;
        maxRequestsAllowedToHold = 10;
        lastLeakedTime = 0L;
        reqCount = 0;
    }
    public boolean rateLimit() throws Exception{
        // so here.. the task is to leak the items continuously..
        // so in leaky bucket when the request arrives at the ratelimiter.. it looks , that , last request arrived some minutes ago///
        // then if the time exhauused is more than enough to let the request.. we will let it 
        long currTime = System.currentTimeMillis();
        int noOfLeaked = (int) ((currTime-lastLeakedTime)/leaksPerMilliSecond);
        clearCurrent(noOfLeaked);
        lastLeakedTime += noOfLeaked * leaksPerMilliSecond;
        if (reqCount < maxRequestsAllowedToHold) {
            reqCount++;
            //return true; // Request accepted into the bucket to be processed at constant leak rate
        }
        else{
            throw new Exception(" Too many requests ");
        }
        return true;
        
    }
    private void clearCurrent(int noOfLeaked){// add synchronised to this to allow one by onw.. 
        if(noOfLeaked > 0){
            if(reqCount <= noOfLeaked){
                
                noOfLeaked = noOfLeaked - reqCount;
                reqCount = 0;
            }
            else{
                reqCount = reqCount - noOfLeaked;
                noOfLeaked = 0;
            }
        }
    }
}
