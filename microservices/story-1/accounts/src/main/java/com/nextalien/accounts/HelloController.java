package com.nextalien.accounts;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class HelloController {

    @GetMapping("sayHello2")
    public String sayHello(){
        return "Hellow World from batch 4 updated...";
    }
}
