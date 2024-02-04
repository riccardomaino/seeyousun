package com.taass.seeyousun.resortreservationservice.config;

import com.taass.seeyousun.resortreservationservice.repositories.ResortClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ResortClientConfig {

    @Bean
    public ResortClient resortClient() {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://resort-service/")
                .build();
        WebClientAdapter adapter = WebClientAdapter.create(webClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(ResortClient.class);
    }
}