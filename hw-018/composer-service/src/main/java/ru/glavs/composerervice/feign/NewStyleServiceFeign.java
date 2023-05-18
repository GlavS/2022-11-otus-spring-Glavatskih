package ru.glavs.composerervice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@FeignClient(name = "newstyle-service")
public interface NewStyleServiceFeign {
    @GetMapping("/newstyleof/{oldStyleDate}")
    LocalDate convertToNewStyle(@PathVariable String oldStyleDate);
}
