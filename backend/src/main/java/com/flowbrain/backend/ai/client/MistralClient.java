package com.flowbrain.backend.ai.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.flowbrain.backend.ai.dto.Message;
import com.flowbrain.backend.ai.dto.MistralRequest;
import com.flowbrain.backend.ai.dto.MistralResponse;

@Component
public class MistralClient {

        private final WebClient webClient;

        @Value("${mistral.api.key}")
        private String apiKey;

        @Value("${mistral.base.url}")
        private String baseUrl;

        @Value("${mistral.model}")
        private String model;

        public MistralClient(WebClient webClient) {
                this.webClient = webClient;
        }

        public String chat(String prompt) {

                MistralRequest request = new MistralRequest(
                                model,
                                List.of(new Message("user", prompt)));

                MistralResponse response = webClient

                                .post()

                                .uri(baseUrl)

                                .header(
                                                HttpHeaders.AUTHORIZATION,
                                                "Bearer " + apiKey)

                                .contentType(MediaType.APPLICATION_JSON)

                                .bodyValue(request)

                                .retrieve()

                                .bodyToMono(MistralResponse.class)

                                .block();

                if (response == null
                                || response.getChoices() == null
                                || response.getChoices().isEmpty()) {

                        throw new RuntimeException(
                                        "No response received from Mistral");
                }

                return response
                                .getChoices()
                                .get(0)
                                .getMessage()
                                .getContent();
        }

}