/*package com.fitness.aiservice.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Map;

@Service
public class GeminiService {

    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String getAnswer(String question) {
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", question)
                        })
                }
        );

        String response = webClient.post()
                .uri(geminiApiUrl + geminiApiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                //.retryWhen(Retry.backoff(50, Duration.ofSeconds(40)))
//                .onErrorResume(e -> {
//                    //log.error("Gemini API call failed", e);
//                    return Mono.just("{\"analysis\":{},\"improvements\":[],\"suggestions\":[],\"safety\":[]}");
//                })
                .block();

        return response;
    }
}*/

package com.fitness.aiservice.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Map;

@Service
public class GeminiService {

    private final WebClient webClient;

    // Use these for the full endpoint: e.g., https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent
    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public GeminiService(WebClient.Builder webClientBuilder) {
        // Build the WebClient here, but only set the base headers
        this.webClient = webClientBuilder
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public String getAnswer(String question) {
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", question)
                        })
                }
        );

        // Append the API key as a query parameter in the URI
        String uriWithKey = geminiApiUrl + "?key=" + geminiApiKey;

        return webClient.post()
                .uri(uriWithKey) // Corrected URI with the API key
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(2)))
                .onErrorResume(e -> {
                    // log.error("Gemini API call failed", e);
                    return Mono.just("{\"error\":\"Gemini API call failed\"}");
                })
                .block();
    }
}
