package ru.otus.glavs.service.loader;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@ComponentScan({"ru.otus.glavs.properties", "ru.otus.glavs.service.loader"})
public class LoaderTestConfiguration {
    @MockBean
    private Loader txtFileLoader;
}
