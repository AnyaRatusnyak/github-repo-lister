package com.example.githubrepolister.config;

import static com.example.githubrepolister.constants.Constants.GITHUB_API_BASE_URL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                .baseUrl(GITHUB_API_BASE_URL);
    }
}
