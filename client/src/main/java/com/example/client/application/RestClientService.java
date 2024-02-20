package com.example.client.application;

import com.example.client.domain.RestClient;
import com.example.client.dto.MemberDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;


public class RestClientService {

    private final Logger LOGGER = LoggerFactory.getLogger(RestClientService.class);
    private final RestClient restClient;

    public RestClientService(final RestClient restClient) {
        this.restClient = restClient;
    }

    public String getHello() {
        ResponseEntity<String> responseEntity = restClient.getHello();

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    public String getNameWithQueryParam(final String name) {
        ResponseEntity<String> responseEntity = restClient.getNameWithQueryParam(name);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    public String getNameWithPathVariable(final String name) {
        ResponseEntity<String> responseEntity = restClient.getNameWithPathVariable(name);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    public MemberDto postWithRequestBody(final String name) {
        ResponseEntity<MemberDto> responseEntity = restClient.postWithRequestBody(name);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    public MemberDto postWithRequestHeaderAndBody(final String header, final String name) {
        ResponseEntity<MemberDto> responseEntity = restClient.postWithRequestHeaderAndBody(header, name);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }
}
