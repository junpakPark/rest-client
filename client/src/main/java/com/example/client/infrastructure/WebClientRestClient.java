package com.example.client.infrastructure;

import com.example.client.domain.RestClient;
import com.example.client.dto.MemberDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientRestClient implements RestClient {

    private final WebClient webClient;

    public WebClientRestClient(@Value("${rest.client.base.url}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Override
    public ResponseEntity<String> getHello() {
        return webClient.get()
                .uri("/test")
                .retrieve()
                .toEntity(String.class)
                .block();
    }

    @Override
    public ResponseEntity<String> getNameWithQueryParam(final String name) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/test/name")
                        .queryParam("name", name)
                        .build())
                .retrieve()
                .toEntity(String.class)
                .block();
    }

    @Override
    public ResponseEntity<String> getNameWithPathVariable(final String name) {
        return webClient.get()
                .uri("/test/name/{name}", name)
                .retrieve()
                .toEntity(String.class)
                .block();
    }

    @Override
    public ResponseEntity<MemberDto> postWithRequestBody(final String name) {
        return webClient.post()
                .uri("/test/member")
                .bodyValue(new MemberDto(name))
                .retrieve()
                .toEntity(MemberDto.class)
                .block();
    }

    @Override
    public ResponseEntity<MemberDto> postWithRequestHeaderAndBody(final String header, final String name) {
        return webClient.post()
                .uri("/test/add-header")
                .header("auth", header)
                .bodyValue(new MemberDto(name))
                .retrieve()
                .toEntity(MemberDto.class)
                .block();
    }
}
