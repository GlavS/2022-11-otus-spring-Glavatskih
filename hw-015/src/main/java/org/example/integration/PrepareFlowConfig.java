package org.example.integration;

import lombok.AllArgsConstructor;
import org.example.ingredient.Carrots;
import org.example.ingredient.Ingredient;
import org.example.ingredient.Onions;
import org.example.ingredient.Roast;
import org.example.service.BoilingService;
import org.example.service.RoastService;
import org.example.utensil.FryingPan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

import java.util.Map;

@Configuration
@AllArgsConstructor
public class PrepareFlowConfig {

    private final BoilingService boilingService;

    private final RoastService roastService;


    @Bean
    public IntegrationFlow finalPrepareRouterFlow() {
        return IntegrationFlows.from("cutResultProductChannel")
                .<Ingredient, String>route(payload -> payload.getClass().getSimpleName(),
                        mapping -> mapping.suffix("Channel")
                                .channelMapping("Beans", "boilBeans")
                                .channelMapping("Carrots", "sortCarrots")
                                .channelMapping("Onions", "prepareRoast"))
                .get();
    }


    @Bean
    public IntegrationFlow boilBeansFlow() {
        return IntegrationFlows.from(boilBeansChannel())
                .handle(boilingService, "cookBeans")
                .channel("prepareSoupChannel")
                .get();
    }

    @Bean
    public IntegrationFlow boilCarrotsFlow() {
        return IntegrationFlows.from(boilCarrotsChannel())
                .channel("prepareSoupChannel")
                .get();
    }

    @Bean
    public IntegrationFlow sortCarrotsFlow() {
        return IntegrationFlows.from(sortCarrotsChannel())
                .<Carrots, Boolean>route(payload -> payload.getName().equals("carrotsToBoil"),
                        mapping -> mapping.suffix("Channel")
                                .channelMapping(true, "boilCarrots")
                                .channelMapping(false, "prepareRoast"))
                .get();
    }

    @Bean
    public IntegrationFlow prepareRoastFlow() {
        return IntegrationFlows.from(prepareRoastChannel())
                .enrichHeaders(Map.of("correlationID", "12345"))
                .aggregate(
                        aggregatorSpec ->
                                aggregatorSpec
                                        .correlationStrategy(message -> message.getHeaders().get("correlationID"))
                                        .releaseStrategy(group -> group.size() == 2)
                                        .outputProcessor(
                                                group -> {
                                                    Onions onions = null;
                                                    Carrots carrots = null;
                                                    for (Message<?> message : group.getMessages()) {
                                                        if (message.getPayload() instanceof Onions) {
                                                            onions = (Onions) message.getPayload();
                                                        } else if (message.getPayload() instanceof Carrots) {
                                                            carrots = (Carrots) message.getPayload();
                                                        }
                                                    }
                                                    assert onions != null;
                                                    assert carrots != null;

                                                    Roast roast = new Roast(onions, carrots);
                                                    FryingPan fryingPan = roastService.cookRoast(roast);
                                                    return new GenericMessage<>(fryingPan.getRoast());
                                                }
                                        )
                )
                .channel(prepareSoupChannel())
                .get();
    }

    @Bean
    public MessageChannel prepareRoastChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public MessageChannel sortCarrotsChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public MessageChannel boilBeansChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public MessageChannel prepareSoupChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public MessageChannel boilCarrotsChannel() {
        return MessageChannels.direct().get();
    }
}
