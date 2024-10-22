package ru.collection.doll_collection.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendEmailConfig {

    @Value("${recipient}")
    private String recipient;
    @Value("${sender}")
    private String sender;
    @Value("${addressFile}")
    private String addressFile;


    @Bean
    public String recipient() {
        return recipient;
    }
    @Bean
    public String sender() {
        return sender;
    }
    @Bean
    public String addressFile() {
        return addressFile;
    }
}
