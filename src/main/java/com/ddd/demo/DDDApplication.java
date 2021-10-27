package com.ddd.demo;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author wl
 * @date 2021 -9-15
 */
@SpringBootApplication
@EnableAsync
public class DDDApplication {
    public static void main(String[] args) {
        System.out.println("ddd—demo-领域设计开始启动...........");
        new SpringApplicationBuilder(DDDApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
        System.out.println("ddd—demo-领域设计启动完成............");
    }
}
