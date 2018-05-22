package com.iexec.poco.api;


import com.iexec.scheduler.ethereum.IexecSchedulerLibrary;
import com.iexec.scheduler.workerpool.WorkerPoolService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class Application {

    public static void main(String[] args) {
        IexecSchedulerLibrary.initialize("./src/main/resources/iexec-scheduler.yml");
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.iexec.poco.api" ))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "PoCo Visualizer",
                "Visualization API for PoCo. Plug on pool " + WorkerPoolService.getInstance().getWorkerPool().getContractAddress(),
                "v1",
                "Terms of service",
                new Contact("", "https://github.com/iExecBlockchainComputing/iexec-poco-api", ""),
                "License of API", "API license URL", Collections.emptyList());
    }
}