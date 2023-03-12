package ru.glavs.hw011.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.*;
import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class StaticRouter {

    @Value("classpath:/templates/list.html")
    private Resource list;

    @Value("classpath:/templates/show.html")
    private Resource bookShow;


    @Bean
    public RouterFunction<ServerResponse> staticPages() {
        return route().GET("/", request -> ok().contentType(MediaType.TEXT_HTML).bodyValue(list))
                .GET("/book-show", request -> request.queryParam("id").map(p -> ok().contentType(MediaType.TEXT_HTML).bodyValue(bookShow)).orElse(badRequest().build()))
                .build();
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
