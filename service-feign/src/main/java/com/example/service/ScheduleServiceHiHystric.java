package com.example.service;

import org.springframework.stereotype.Component;

/**
 * ScheduleServiceHiHystric
 *
 * @author huzunrong
 * @since 1.0.0
 */
@Component
public class ScheduleServiceHiHystric implements ScheduleServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry " + name;
    }
}
