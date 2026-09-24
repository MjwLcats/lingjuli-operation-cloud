package com.lingjuli.store;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = {"com.lingjuli.store", "com.lingjuli.foundation"})
public class LingjuliStoreApplication { public static void main(String[] args) { SpringApplication.run(LingjuliStoreApplication.class, args); } }
