package ru.glavs.hw008.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.*;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class StaticRouter {
    @Bean
    public RouterFunction<ServerResponse> listPage(@Value("classpath:/templates/list.html") Resource html) {
        return route().GET("/", request -> ok().contentType(MediaType.TEXT_HTML).bodyValue(html)).build();
    }

    @Bean
    RouterFunction<ServerResponse> staticResourcePath(){
        return resources("/**", new ClassPathResource("static/"));
    }
    @Bean
    RouterFunction<ServerResponse> templatesResourcePath(){
        return resources("/**", new ClassPathResource("templates/"));
    }

}
