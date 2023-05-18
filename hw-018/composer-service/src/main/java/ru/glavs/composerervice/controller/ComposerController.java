package ru.glavs.composerervice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.glavs.composerervice.feign.NewStyleServiceFeign;
import ru.glavs.composerervice.model.Composer;
import ru.glavs.composerervice.model.ComposerProjection;
import ru.glavs.composerervice.repository.ComposerRepository;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class ComposerController {
    private final NewStyleServiceFeign feign;
    private final ComposerRepository repository;

    @GetMapping("/composer/{family}")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    }, fallbackMethod = "getEmptyNewStyle")
    public ComposerProjection getComposerData(@PathVariable String family){

        Composer composer = repository.findFirstByFamilyName(family);
        LocalDate birthdate = composer.getBirthDate();
        LocalDate newStyleBirthdate = feign.convertToNewStyle(birthdate.toString());


        return new ComposerProjection(composer.getId(),
                composer.getFamilyName(),
                composer.getBirthDate(),
                newStyleBirthdate);
    }

    private ComposerProjection getEmptyNewStyle(String family) {
        return new ComposerProjection(0L,
                family + ": Service non available",
                null,
                null);
    }
}
