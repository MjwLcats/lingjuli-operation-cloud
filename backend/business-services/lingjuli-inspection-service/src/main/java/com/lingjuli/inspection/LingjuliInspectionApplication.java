package com.lingjuli.inspection;
import org.springframework.boot.SpringApplication; import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages={"com.lingjuli.inspection","com.lingjuli.foundation"}) public class LingjuliInspectionApplication { public static void main(String[] args){SpringApplication.run(LingjuliInspectionApplication.class,args);} }
