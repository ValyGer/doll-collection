package ru.collection.doll_collection.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import ru.collection.doll_collection.client.DollManagerClientImpl;

@Configuration
public class ClientBean {

    @Bean
    public DollManagerClientImpl dollManagerClient() {
        return new DollManagerClientImpl(RestClient.builder()
                .baseUrl("http://localhost:8080")
                .build());
    }

}
