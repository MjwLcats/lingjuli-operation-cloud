package com.lingjuli.identity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = {"com.lingjuli.identity", "com.lingjuli.foundation"})
public class LingjuliIdentityApplication { public static void main(String[] args) { SpringApplication.run(LingjuliIdentityApplication.class, args); } }
