package ru.otus.glavs.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(Application.class)
public class PropertiesAutoConf {
}
