package com.sgevf.spreaderserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.sgevf.spreaderserver.dao")
@EnableTransactionManagement
public class SpreaderserverApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpreaderserverApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpreaderserverApplication.class, args);
    }

}
