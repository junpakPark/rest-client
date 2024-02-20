package com.example.client.infrastructure;

import com.example.client.domain.RestClient;
import com.example.client.dto.MemberDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface HttpInterfaceRestClient extends RestClient {
    @GetExchange("/test")
    ResponseEntity<String> getHello();

    @GetExchange("/test/name")
    ResponseEntity<String> getNameWithQueryParam(@RequestParam("name") final String name);

    @GetExchange("/test/name/{name}")
    ResponseEntity<String> getNameWithPathVariable(@PathVariable final String name);

    @PostExchange("/test/member")
    ResponseEntity<MemberDto> postWithRequestBody(@RequestBody final MemberDto memberDto);

    @PostExchange("/test/add-header")
    ResponseEntity<MemberDto> postWithRequestHeaderAndBody(
            @RequestHeader("auth") final String header,
            @RequestBody final MemberDto memberDto
    );
}
