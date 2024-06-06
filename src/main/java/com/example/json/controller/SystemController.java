package com.example.json.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SystemController {

    @GetMapping("/system/get-version")
    public ResponseEntity<String> getVersion() {
        log.info("getVersion");
        log.info("password : 213546");
        log.info("\"password\": \"mysecretpassword\"");
        log.error("getVersion123");
        return ResponseEntity.status(HttpStatus.OK).body("1.0.0");
    }
}
