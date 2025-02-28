package com.zll.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.zll")
@EnableTransactionManagement //开启注解方式的事务管理
@Slf4j
public class BookServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookServerApplication.class, args);
    }
}
