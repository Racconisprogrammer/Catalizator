package com.example.catalizator.config;


import com.example.catalizator.handlers.GreetingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Map;

@Configuration
public class GreetingRouter {

    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/hello")
                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), greetingHandler::hello)
                .andRoute(RequestPredicates.GET("/"),
                        serverRequest -> {
                    String user = serverRequest.queryParam("user")
                            .orElse("nobody");
                            return ServerResponse
                                    .ok()
                                    .render("index", Map.of("user", user));
                        });
    }

}
