package com.example.githubrepolister.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    public static final String GITHUB_API_BASE_URL = "https://api.github.com";

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                .baseUrl(GITHUB_API_BASE_URL);
    }
}
