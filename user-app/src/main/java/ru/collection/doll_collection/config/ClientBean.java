package ru.collection.doll_collection.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestClient;
import ru.collection.doll_collection.client.DollUserClientImpl;

@Configuration
public class ClientBean {

    @Bean
    public DollUserClientImpl dollUserClient(
            @Value("${catalog_service}") String serviceUserName,
            @Value("${catalog_service_token}") String serviceUserPassword,
            @Value("${catalog_service_url}") String url
    ) {
        return new DollUserClientImpl(RestClient.builder()
                .baseUrl(url)
                .requestInterceptor(new BasicAuthenticationInterceptor(serviceUserName, serviceUserPassword))
                .build());
    }
}
