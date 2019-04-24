package com.stone.springbooteurekaproducer.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author honorstone
 * @date 2019/4/24 10:03
 */
@RestController
public class HomeController {
    @RequestMapping("/hello")

    public String index(@RequestParam String name) {
        return "hello "+name+"，this is first messge";
    }
}
