package com.iexec.scheduler.mock;


import com.iexec.scheduler.ethereum.IexecSchedulerLibrary;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        IexecSchedulerLibrary.initialize("./src/main/resources/iexec-scheduler.yml");
        SpringApplication.run(Application.class, args);
    }
}