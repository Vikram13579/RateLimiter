package com.mock.ratelimiter.Interface.Impl;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicIntegerArray;

import com.mock.ratelimiter.Interface.RateLimitStrategy;

public class SlidingWindowStrategy  implements RateLimitStrategy{
    long windowLength ;
    int requestCount;
    int maxReqAllowed;
    AtomicIntegerArray array ;
    long[] bucketTimeStamp;
    long bucketTimeStampSize;
    int totalBuckets;
    long totalBucketLengthInMillis;
    Queue<Long> queue = new LinkedList<>();
    public SlidingWindowStrategy(){
        windowLength = 600;
        requestCount = 0;
        maxReqAllowed = 10;
        array = new AtomicIntegerArray(100);
        bucketTimeStamp = new long[100];
        totalBuckets = 100;
        totalBucketLengthInMillis = windowLength/totalBuckets;
    }
    public boolean rateLimit()throws Exception{
        return reqAllowed();
        //long currentTime = System.currentTimeMillis();
        // while(queue.peek() < (windowLength - currentTime)){
        //     queue.poll(); // but as queue takes lot of time .. there is another strategy with this.. that is storying the buckets into array
        //     // its like , we would keep the things into the array and 
        //     // lets say windowlength 10000 millis maxreqallowed...1000 ... total buckets = 100 ... bucketlength in millis = 100
        //     // buckets and buckettimestamps..
        //     // current time .. 11000 - millis  current bucket index = currtime / bucket length in millis  = 110 % totalbuckets = 100 = 10 timestamp = 110 %100 = 10
        //     // totoal buckets mei ... if current time - bucketTimeStamp > windowlength = set that to 0
        //     // currenttimestamp != curr then change and  set buckets to 0 
        // }
        // if(queue.size() > maxReqAllowed){
        //     throw new Exception(" Too many requests ");
        // }
        // queue.offer(currentTime);
        // return true;
    }
    public boolean reqAllowed() throws Exception{
        long currentTime = System.currentTimeMillis();

        int currentBucketIndex = (int) ((currentTime /totalBucketLengthInMillis)%totalBuckets);
        long currentTimeStamp = (currentTime / totalBucketLengthInMillis )*totalBucketLengthInMillis;

        for(int i = 0;i<totalBuckets;i++){
            if(currentTime -bucketTimeStamp[i] > windowLength){
                array.set(i,0);
            }
        }

        if(bucketTimeStamp[currentBucketIndex] != currentTimeStamp){
            bucketTimeStamp[currentBucketIndex] = currentTimeStamp;
            array.set(currentBucketIndex,0);
        }

        int totalReqInWindow =0;
        for(int i = 0;i<totalBuckets;i++){
            totalReqInWindow += array.get(i);
        }  


        if(totalReqInWindow >= maxReqAllowed){
            throw new Exception("Too many requests ");
        }

        array.incrementAndGet(currentBucketIndex);
        return true;


    }
}
