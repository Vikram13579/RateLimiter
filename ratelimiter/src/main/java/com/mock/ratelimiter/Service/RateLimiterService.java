package main.java.com.mock.ratelimiter.Service;
@Transaction
public class RateLimiterService {
    RateLimiterStrategy strategy;
    RateLimitFactory factory;
    @Autowired
    public void rateLimit(RateLimitType rateLimitType) throws Exception{
        // so here..
        // methods to ratelimit.. fixed window counter..
        // sliding window algorithm
        // leaky bucket
        // token bucket algorithms..
        strategy = RateLimitFactory.getInstance(rateLimitType);
        return strategy.ratelimit();

    }
}
