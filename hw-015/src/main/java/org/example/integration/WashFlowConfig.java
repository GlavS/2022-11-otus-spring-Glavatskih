package org.example.integration;

import lombok.AllArgsConstructor;
import org.example.ingredient.Beans;
import org.example.ingredient.Carrots;
import org.example.ingredient.Onions;
import org.example.service.WashService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

@Configuration
@AllArgsConstructor
public class WashFlowConfig {

    private final WashService washService;

    @Bean
    @InboundChannelAdapter(value = "washChannel", poller = @Poller(fixedRate = "360000"))
    public MessageSource<Carrots> carrotsMessageSource() {
        return () -> new GenericMessage<>(new Carrots(1000));
    }

    @Bean
    @InboundChannelAdapter(value = "washChannel", poller = @Poller(fixedRate = "360000"))
    public MessageSource<Beans> beansMessageSource() {
        return () -> new GenericMessage<>(new Beans(500));
    }

    @Bean
    @InboundChannelAdapter(value = "cleanProductsChannel", poller = @Poller(fixedRate = "360000"))
    public MessageSource<Onions> onionsMessageSource() {
        return () -> new GenericMessage<>(new Onions(500));
    }


    @Bean
    public IntegrationFlow washFlow() {
        return IntegrationFlows.from(washChannel())
                .handle(washService, "washProduct")
                .channel(cleanProductsChannel())
                .get();
    }

    @Bean
    public MessageChannel washChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public MessageChannel cleanProductsChannel() {
        return MessageChannels.publishSubscribe().get();
    }
}
