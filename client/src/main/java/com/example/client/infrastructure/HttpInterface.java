package com.example.client.infrastructure;

import com.example.client.dto.MemberDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface HttpInterface {
    @GetExchange("/test")
    ResponseEntity<String> getHello();

    @GetExchange("/test/name")
    ResponseEntity<String> getNameWithQueryParam(@RequestParam("name") String name);

    @GetExchange("/test/name/{name}")
    ResponseEntity<String> getNameWithPathVariable(@PathVariable String name);

    @PostExchange("/test/member")
    ResponseEntity<MemberDto> postWithRequestBody(@RequestBody MemberDto memberDto);

    @PostExchange("/test/add-header")
    ResponseEntity<MemberDto> postWithRequestHeaderAndBody(
            @RequestHeader("auth") String header,
            @RequestBody MemberDto memberDto
    );
}
