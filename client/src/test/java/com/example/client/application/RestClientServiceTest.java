package com.example.client.application;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.example.client.dto.MemberDto;
import com.example.client.infrastructure.RestTemplateRestClient;
import com.example.client.infrastructure.WebClientRestClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestClientServiceTest {

    @Autowired
    private ClientService clientService;


    @Test
    void restTemplateClient() {

        // when
        String hello = clientService.getHello();
        String name = clientService.getNameWithQueryParam("queryParam");
        String name2 = clientService.getNameWithPathVariable("pathVariable");
        MemberDto memberDto = clientService.postWithRequestBody("requestBody");
        MemberDto memberDto2 = clientService.postWithRequestHeaderAndBody("requestHeader", "requestBody");

        // then
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(hello).isEqualTo("Hello, World!");
            softAssertions.assertThat(name).isEqualTo("Hello, queryParam");
            softAssertions.assertThat(name2).isEqualTo("Hello, pathVariable");
            softAssertions.assertThat(memberDto.name()).isEqualTo("Hello, requestBody");
            softAssertions.assertThat(memberDto2.name()).isEqualTo("requestHeader, requestBody");
        });
    }
}
