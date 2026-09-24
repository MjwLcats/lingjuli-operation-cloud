package com.lingjuli.tenant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = {"com.lingjuli.tenant", "com.lingjuli.foundation"})
public class LingjuliTenantApplication { public static void main(String[] args) { SpringApplication.run(LingjuliTenantApplication.class, args); } }
