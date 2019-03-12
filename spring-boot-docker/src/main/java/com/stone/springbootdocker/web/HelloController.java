package com.stone.springbootdocker.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
