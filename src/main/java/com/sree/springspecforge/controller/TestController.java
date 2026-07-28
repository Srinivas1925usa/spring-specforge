package com.sree.springspecforge.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
class TestController {

    @RequestMapping("/test")
    public String test() {
        return "Hello, World!";
    }
}