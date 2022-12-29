package ru.otus.glavs.properties;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootConfiguration
@EnableConfigurationProperties({Application.class})
public class PropertiesAutoConf {
}
