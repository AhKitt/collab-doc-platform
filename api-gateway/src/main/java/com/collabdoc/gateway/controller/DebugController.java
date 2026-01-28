package com.collabdoc.gateway.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DebugController {

    @Bean
    public RouterFunction<ServerResponse> debugRoutes() {
        return route(GET("/debug"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue("""
                    Gateway Debug Info
                    ==================
                    Endpoints:
                    - GET  /debug           - This page
                    - GET  /api/users       - User service
                    - GET  /eureka          - Eureka console
                    - GET  /eureka/         - Eureka console (with slash)
                    
                    Test commands:
                    curl http://localhost:8080/debug
                    curl http://localhost:8080/api/users
                    curl -v http://localhost:8080/eureka
                    """))

                .andRoute(GET("/"),
                        request -> ServerResponse.ok()
                                .contentType(MediaType.TEXT_PLAIN)
                                .bodyValue("API Gateway Root - Use /debug for available endpoints"));
    }
}