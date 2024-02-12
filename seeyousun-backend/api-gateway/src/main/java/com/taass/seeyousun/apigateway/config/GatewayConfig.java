//package com.taass.seeyousun.apigateway.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class GatewayConfig {
//    @Value("${RESORT_SERVICE_HOST:localhost}")
//    public static String resortServiceHost;
//
//    @Value("${RESORT_RESERVATION_SERVICE_HOST:localhost}")
//    public static String resortReservationServiceHost;
//
//    @Value("${REVIEW_SERVICE_HOST:localhost}")
//    public static String reviewServiceHost;
//
//    @Value("${EVENT_SERVICE_HOST:localhost}")
//    public static String eventServiceHost;
//
//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder){
//        return builder.routes()
//                .route("resort-route", r -> r
//                        .path()
//                        .filters(f -> f.prefixPath("/api/v1"))
//                        .uri("lb://" + resortServiceHost))
//                .build();
//    }
//}