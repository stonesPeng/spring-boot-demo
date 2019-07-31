package com.stone.springbootdocker.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author honorstone
 * @date 2019/3/11 22:12
 */
@RestController
public class HelloController {

    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "Hello Docker!";
    }


    @GetMapping("/now")
    public String now(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
