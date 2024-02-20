package com.example.client.infrastructure;

import com.example.client.domain.RestClient;
import com.example.client.dto.MemberDto;
import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RestTemplateRestClient implements RestClient {

    @Value("${rest.client.base.url}")
    private String baseUrl;
    private final RestTemplate restTemplate;

    public RestTemplateRestClient(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<String> getHello() {
        URI uri = UriComponentsBuilder.fromUriString(baseUrl)
                .path("/test")
                .encode()
                .build()
                .toUri();

        return restTemplate.getForEntity(uri, String.class);
    }

    @Override
    public ResponseEntity<String> getNameWithQueryParam(final String name) {
        URI uri = UriComponentsBuilder.fromUriString(baseUrl)
                .path("/test/name")
                .queryParam("name", name)
                .encode()
                .build()
                .toUri();

        return restTemplate.getForEntity(uri, String.class);
    }

    @Override
    public ResponseEntity<String> getNameWithPathVariable(final String name) {
        URI uri = UriComponentsBuilder.fromUriString(baseUrl)
                .path("/test/name/{name}")
                .encode()
                .build()
                .expand(name)
                .toUri();

        return restTemplate.getForEntity(uri, String.class);
    }

    @Override
    public ResponseEntity<MemberDto> postWithRequestBody(final MemberDto memberDto) {
        URI uri = UriComponentsBuilder.fromUriString(baseUrl)
                .path("/test/member")
                .encode()
                .build()
                .toUri();

        return restTemplate.postForEntity(uri, memberDto, MemberDto.class);
    }


    @Override
    public ResponseEntity<MemberDto> postWithRequestHeaderAndBody(final String header, final MemberDto memberDto) {
        URI uri = UriComponentsBuilder.fromUriString(baseUrl)
                .path("/test/add-header")
                .encode()
                .build()
                .toUri();

        RequestEntity<MemberDto> requestEntity = RequestEntity.post(uri)
                .header("auth", header)
                .body(memberDto);

        return restTemplate.exchange(requestEntity, MemberDto.class);
    }
}
