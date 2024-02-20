package com.example.client.infrastructure;

import com.example.client.domain.RestClient;
import com.example.client.dto.MemberDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientRestClient implements RestClient {

    private final WebClient webClient;

    public WebClientRestClient(final WebClient webClient) {
        this.webClient = webClient;
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
    public ResponseEntity<MemberDto> postWithRequestBody(final MemberDto memberDto) {
        return webClient.post()
                .uri("/test/member")
                .bodyValue(memberDto)
                .retrieve()
                .toEntity(MemberDto.class)
                .block();
    }

    @Override
    public ResponseEntity<MemberDto> postWithRequestHeaderAndBody(final String header, final MemberDto memberDto) {
        return webClient.post()
                .uri("/test/add-header")
                .header("auth", header)
                .bodyValue(memberDto)
                .retrieve()
                .toEntity(MemberDto.class)
                .block();
    }
}
