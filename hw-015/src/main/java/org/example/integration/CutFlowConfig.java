package org.example.integration;

import lombok.RequiredArgsConstructor;
import org.example.ingredient.*;
import org.example.service.CutService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class CutFlowConfig {

    private final CutService cutService;

    @Bean
    public IntegrationFlow getCleanBeansFlow() {
        return IntegrationFlows.from("cleanProductsChannel")
                .filter(payload -> payload instanceof Beans)
                .channel("cutResultProductChannel")
                .get();
    }

    @Bean
    public IntegrationFlow cutProductsRouterFlow() {
        return IntegrationFlows.from("cleanProductsChannel")
                .filter(payload -> !(payload instanceof Beans))
                .<Cutable, Boolean>route(payload -> payload instanceof Carrots,
                        mapping -> mapping.suffix("Channel")
                                .channelMapping(true, "cutCarrots")
                                .channelMapping(false, "cutOnions"))
                .get();
    }

    @Bean
    public IntegrationFlow onionsCutFlow() {
        return IntegrationFlows.from(cutOnionsChannel())
                .enrichHeaders(Map.of("cutType", CutType.FINE))
                .handle(Onions.class,
                        (payload, headers) -> cutService.cutProduct(payload, (CutType) headers.get("cutType"))
                )
                .channel(cutResultProductChannel())
                .get();
    }

    @Bean
    public IntegrationFlow carrotsCutFlow() {
        return IntegrationFlows.from(cutCarrotsChannel())
                .split(Carrots.class, this::carrotsSplitter)
                .handle(Carrots.class,
                        (payload, headers) -> {
                            if (payload.getName().equals("carrotsToBoil")) {
                                cutService.cutProduct(payload, CutType.COARSE);
                            } else {
                                cutService.cutProduct(payload, CutType.FINE);
                            }
                            return payload;
                        }
                )
                .channel(cutResultProductChannel())
                .get();
    }

    @Splitter
    public List<Carrots> carrotsSplitter(Carrots carrots) {
        if (!carrots.isClean()) {
            throw new NotWashedException("Carrots not washed!");
        }
        Carrots carrotsToBoil = new Carrots(carrots.getWeightInGrams() / 2);
        carrotsToBoil.setName("carrotsToBoil");
        carrotsToBoil.setClean(true);

        Carrots carrotsToRoast = new Carrots(carrots.getWeightInGrams() / 2);
        carrotsToRoast.setName("carrotsToRoast");
        carrotsToRoast.setClean(true);

        return List.of(carrotsToBoil, carrotsToRoast);
    }

    @Bean
    public MessageChannel cutCarrotsChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public MessageChannel cutOnionsChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public MessageChannel cutResultProductChannel() {
        return MessageChannels.publishSubscribe().get();
    }
}
