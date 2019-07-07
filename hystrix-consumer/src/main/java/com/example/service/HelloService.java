package com.example.service;

import com.example.command.CustomCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * HelloService
 *
 * @author huzunrong
 * @since 1.0.0
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    //    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        return new CustomCommand(restTemplate).execute();
    }

    public String hiError(String name) {
        return "hi," + name + ",sorry,error!";
    }
}
