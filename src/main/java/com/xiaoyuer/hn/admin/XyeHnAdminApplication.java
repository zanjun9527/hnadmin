package com.xiaoyuer.hn.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xiaoyuer.hn.admin")
public class XyeHnAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(XyeHnAdminApplication.class, args);
    }

}
