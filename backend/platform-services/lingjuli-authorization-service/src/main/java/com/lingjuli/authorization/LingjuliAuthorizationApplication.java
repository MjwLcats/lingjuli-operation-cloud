package com.lingjuli.authorization;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = {"com.lingjuli.authorization", "com.lingjuli.foundation"})
public class LingjuliAuthorizationApplication { public static void main(String[] args) { SpringApplication.run(LingjuliAuthorizationApplication.class, args); } }
