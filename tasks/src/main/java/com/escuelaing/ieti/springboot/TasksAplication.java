package com.escuelaing.ieti.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.escuelaing.ieti.springboot"})
public class TasksAplication {

    public static void main(String[] args) {
        SpringApplication.run(TasksAplication.class, args);
    }

}
