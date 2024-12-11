package ru.collection.doll_collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGateWayServer {
    public static void main(String[] args) {
        SpringApplication.run(ApiGateWayServer.class, args);
    }
}