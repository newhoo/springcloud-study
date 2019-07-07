package com.example.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

/**
 * CustomCommand
 *
 * @author huzunrong
 * @since 1.1.1
 */
public class CustomCommand extends HystrixCommand<String> {

    private RestTemplate restTemplate;

    public CustomCommand(RestTemplate restTemplate) {
        super(
                Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("study-group"))
                      .andCommandKey(HystrixCommandKey.Factory.asKey("aa"))
                      .andCommandPropertiesDefaults(
                              HystrixCommandProperties.Setter()
                                                      .withExecutionTimeoutInMilliseconds(100)
                                                      .withCircuitBreakerSleepWindowInMilliseconds(1000)
                      )
                      .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("hystrix-thread-pool"))
                      .andThreadPoolPropertiesDefaults(
                              HystrixThreadPoolProperties.Setter()
                                                         .withCoreSize(3)
                                                         .withMaxQueueSize(2)
                      )
        );
        this.restTemplate = restTemplate;
    }

    @Override
    protected String run() throws Exception {
        System.err.println(Thread.currentThread().getName());
        Random random = new Random();
        int i = random.nextInt(150);
        Thread.sleep(i);
        return restTemplate.getForObject("http://EUREKA-CLIENT/hello?name=" + "123", String.class);
    }

    @Override
    protected String getFallback() {
        return "服务降级了。。";
    }
}