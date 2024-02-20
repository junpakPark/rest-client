package com.example.client.application;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.example.client.dto.MemberDto;
import com.example.client.infrastructure.HttpInterfaceRestClient;
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

    @Autowired
    private HttpInterfaceRestClient httpInterface;

    @Test
    void restTemplate() {
        // given
        restClientService = new RestClientService(restTemplate);

        // when
        String hello = restClientService.getHello();
        String name = restClientService.getNameWithQueryParam("queryParam1");
        String name2 = restClientService.getNameWithPathVariable("pathVariable1");
        MemberDto memberDto = restClientService.postWithRequestBody("requestBody1");
        MemberDto memberDto2 = restClientService.postWithRequestHeaderAndBody("requestHeader1", "requestBody1");

        // then
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(hello).isEqualTo("Hello, World!");
            softAssertions.assertThat(name).isEqualTo("Hello, queryParam1");
            softAssertions.assertThat(name2).isEqualTo("Hello, pathVariable1");
            softAssertions.assertThat(memberDto.name()).isEqualTo("Hello, requestBody1");
            softAssertions.assertThat(memberDto2.name()).isEqualTo("requestHeader1, requestBody1");
        });
    }

    @Test
    void webClient() {
        // given
        restClientService = new RestClientService(webClient);

        // when
        String hello = restClientService.getHello();
        String name = restClientService.getNameWithQueryParam("queryParam2");
        String name2 = restClientService.getNameWithPathVariable("pathVariable2");
        MemberDto memberDto = restClientService.postWithRequestBody("requestBody2");
        MemberDto memberDto2 = restClientService.postWithRequestHeaderAndBody("requestHeader2", "requestBody2");

        // then
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(hello).isEqualTo("Hello, World!");
            softAssertions.assertThat(name).isEqualTo("Hello, queryParam2");
            softAssertions.assertThat(name2).isEqualTo("Hello, pathVariable2");
            softAssertions.assertThat(memberDto.name()).isEqualTo("Hello, requestBody2");
            softAssertions.assertThat(memberDto2.name()).isEqualTo("requestHeader2, requestBody2");
        });
    }

    @Test
    void httpInterface() {
        // given
        restClientService = new RestClientService(httpInterface);

        // when
        String hello = restClientService.getHello();
        String name = restClientService.getNameWithQueryParam("queryParam3");
        String name2 = restClientService.getNameWithPathVariable("pathVariable3");
        MemberDto memberDto = restClientService.postWithRequestBody("requestBody3");
        MemberDto memberDto2 = restClientService.postWithRequestHeaderAndBody("requestHeader3", "requestBody3");

        // then
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(hello).isEqualTo("Hello, World!");
            softAssertions.assertThat(name).isEqualTo("Hello, queryParam3");
            softAssertions.assertThat(name2).isEqualTo("Hello, pathVariable3");
            softAssertions.assertThat(memberDto.name()).isEqualTo("Hello, requestBody3");
            softAssertions.assertThat(memberDto2.name()).isEqualTo("requestHeader3, requestBody3");
        });
    }
}
