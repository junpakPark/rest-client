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

    private RestClientService restClientService;

    @Autowired
    private RestTemplateRestClient restTemplate;

    @Autowired
    private WebClientRestClient webClient;

    @Test
    void restTemplate() {
        // given
        restClientService = new RestClientService(restTemplate);

        // when
        String hello = restClientService.getHello();
        String name = restClientService.getNameWithQueryParam("queryParam");
        String name2 = restClientService.getNameWithPathVariable("pathVariable");
        MemberDto memberDto = restClientService.postWithRequestBody("requestBody");
        MemberDto memberDto2 = restClientService.postWithRequestHeaderAndBody("requestHeader", "requestBody");

        // then
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(hello).isEqualTo("Hello, World!");
            softAssertions.assertThat(name).isEqualTo("Hello, queryParam");
            softAssertions.assertThat(name2).isEqualTo("Hello, pathVariable");
            softAssertions.assertThat(memberDto.name()).isEqualTo("Hello, requestBody");
            softAssertions.assertThat(memberDto2.name()).isEqualTo("requestHeader, requestBody");
        });
    }

    @Test
    void webClient() {
        // given
        restClientService = new RestClientService(webClient);

        // when
        String hello = restClientService.getHello();
        String name = restClientService.getNameWithQueryParam("queryParam");
        String name2 = restClientService.getNameWithPathVariable("pathVariable");
        MemberDto memberDto = restClientService.postWithRequestBody("requestBody");
        MemberDto memberDto2 = restClientService.postWithRequestHeaderAndBody("requestHeader", "requestBody");

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
