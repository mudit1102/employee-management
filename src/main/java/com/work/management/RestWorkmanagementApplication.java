package com.work.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@RefreshScope
@SpringBootApplication
@EnableEurekaClient
public class RestWorkmanagementApplication {

  public static void main(String[] args) {
    SpringApplication.run(RestWorkmanagementApplication.class, args);
  }

}
