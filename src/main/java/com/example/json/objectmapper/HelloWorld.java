package com.example.json.objectmapper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @GetMapping()
    public String test(){
        return  "HelloWorld";
    }
}
