package com.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ScheduleServiceHi
 *
 * @author huzunrong
 * @since 1.0.0
 */
@FeignClient(value = "eureka-client", fallback = ScheduleServiceHiHystric.class)
public interface ScheduleServiceHi {

    @GetMapping(value = "/hello")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
