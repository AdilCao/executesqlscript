package com.kd.execute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Adil
 * @date 2020-04-14 11:10:48
 */
@SpringBootApplication
public class ExecuteSqlScript {

    public static void main(String[] args) {
        System.out.println("=========SpringBoot Start============");
        SpringApplication.run(ExecuteSqlScript.class, args);
    }
}
