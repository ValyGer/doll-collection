package ru.collection.doll_collection.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestClient;
import ru.collection.doll_collection.client.DollUserClientImpl;

@Configuration
public class ClientBean {

    @Bean
    public DollUserClientImpl dollUserClient() {
        String serviceUserName = "catalog-service-user";
        String serviceUserPassword = "Y2F0YWxvZy1zZXJ2aWNlLXVzZXI6cGFzc3dvcmQ=";
        return new DollUserClientImpl(RestClient.builder()
                .baseUrl("http://localhost:8080")
                .requestInterceptor(new BasicAuthenticationInterceptor(serviceUserName, serviceUserPassword))
                .build());
    }

}
