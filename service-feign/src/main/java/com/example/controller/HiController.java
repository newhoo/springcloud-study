package com.example.controller;

import com.example.service.ScheduleServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloControler
 *
 * @author huzunrong
 * @since 1.0.0
 */
@RestController
public class HiController {

    @Autowired
    ScheduleServiceHi scheduleServiceHi;

    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return scheduleServiceHi.sayHiFromClientOne(name);
    }
}