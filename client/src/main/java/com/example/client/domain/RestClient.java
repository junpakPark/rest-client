package com.example.client.domain;

import com.example.client.dto.MemberDto;
import org.springframework.http.ResponseEntity;

public interface RestClient {

    ResponseEntity<String> getHello();

    ResponseEntity<String> getNameWithQueryParam(final String name);

    ResponseEntity<String> getNameWithPathVariable(final String name);

    ResponseEntity<MemberDto> postWithRequestBody(final MemberDto memberDto);

    ResponseEntity<MemberDto> postWithRequestHeaderAndBody(final String header, final MemberDto memberDto);

}
