package org.funnylife.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@EnableAutoConfiguration
@RestController
@RequestMapping("/hello")
public class HelloWorld {
    @Value("${app.name}")
    private String test;

    @RequestMapping("/{name}")
    public String view(@PathVariable("name") String name) {
        return "hello " + name + "<br>appName : " + test;
    }  

}  