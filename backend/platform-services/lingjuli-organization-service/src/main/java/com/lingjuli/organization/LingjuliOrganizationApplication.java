package com.lingjuli.organization;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = {"com.lingjuli.organization", "com.lingjuli.foundation"})
public class LingjuliOrganizationApplication { public static void main(String[] args) { SpringApplication.run(LingjuliOrganizationApplication.class, args); } }
