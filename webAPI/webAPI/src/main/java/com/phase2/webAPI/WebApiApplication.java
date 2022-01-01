package com.phase2.webAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


//@SpringBootApplication(/*exclude = {DataSourceAutoConfiguration.class}*/)
@SpringBootApplication(scanBasePackages = "com.phase2.webAPI")
public class WebApiApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebApiApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WebApiApplication.class, args);
    }
}
