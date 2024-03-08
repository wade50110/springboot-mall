package com.example.json.config;

import com.example.json.JsonApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 要包成war檔案時，需要繼承SpringBootServletInitializer並覆寫configure方法
 */
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JsonApplication.class);
    }
}