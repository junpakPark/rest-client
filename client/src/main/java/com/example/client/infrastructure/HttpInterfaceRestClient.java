package com.example.client.infrastructure;

import com.example.client.domain.RestClient;
import com.example.client.dto.MemberDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class HttpInterfaceRestClient implements RestClient {

    private final HttpInterface httpInterface;

    public HttpInterfaceRestClient(final HttpInterface httpInterface) {
        this.httpInterface = httpInterface;
    }

    @Override
    public ResponseEntity<String> getHello() {
        return httpInterface.getHello();
    }

    @Override
    public ResponseEntity<String> getNameWithQueryParam(final String name) {
        return httpInterface.getNameWithQueryParam(name);
    }

    @Override
    public ResponseEntity<String> getNameWithPathVariable(final String name) {
        return httpInterface.getNameWithPathVariable(name);
    }

    @Override
    public ResponseEntity<MemberDto> postWithRequestBody(final String name) {
        return httpInterface.postWithRequestBody(new MemberDto(name));
    }

    @Override
    public ResponseEntity<MemberDto> postWithRequestHeaderAndBody(final String header, final String name) {
        return httpInterface.postWithRequestHeaderAndBody(header, new MemberDto(name));
    }
}
