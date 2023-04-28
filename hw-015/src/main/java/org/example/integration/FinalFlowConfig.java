package org.example.integration;

import lombok.RequiredArgsConstructor;
import org.example.ingredient.Carrots;
import org.example.ingredient.Roast;
import org.example.service.BoilingService;
import org.example.utensil.Pot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class FinalFlowConfig {

    private final BoilingService boilingService;

    @Bean
    public IntegrationFlow prepareSoupFlow() {
        return IntegrationFlows.from("prepareSoupChannel")
                .enrichHeaders(Map.of("cookId", "54321"))
                .aggregate(
                        aggregatorSpec ->
                                aggregatorSpec
                                        .correlationStrategy(message -> message.getHeaders().get("cookId"))
                                        .releaseStrategy(group -> group.size() == 3)
                                        .outputProcessor(
                                                group -> new GenericMessage<>(new ArrayList<>(group.getMessages()))
                                        )
                )
                .handle(
                        (payload, headers) -> {
                            Pot pot = null;
                            Carrots carrotsToBoil = null;
                            Roast roast = null;
                            List<Message<?>> messageList = (List<Message<?>>) payload;
                            for (Message<?> m : messageList) {
                                if (m.getPayload() instanceof Pot) {
                                    pot = (Pot) m.getPayload();
                                }
                                if (m.getPayload() instanceof Carrots) {
                                    carrotsToBoil = (Carrots) m.getPayload();
                                }
                                if (m.getPayload() instanceof Roast) {
                                    roast = (Roast) m.getPayload();
                                }
                            }
                            assert pot != null;
                            assert carrotsToBoil != null;
                            assert roast != null;

                            pot = boilingService.cookCarrots(pot, carrotsToBoil);
                            pot = boilingService.cookBeanSoup(pot, roast);

                            if (pot.getBeans().isCooked()
                                    && pot.getCarrots().isCooked()
                                    && pot.getRoast().isCooked()) {
                                System.out.println("Soup is ready!");
                            }
                            return null;
                        }
                )
                .get();
    }
}
