package com.example.testapi.presentation;

import com.example.testapi.dto.MemberDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestApiController.class);

    @GetMapping
    public String getTest1() {
        LOGGER.info("getTest1 호출");

        return "Hello, World!";
    }

    @GetMapping("/name")
    public String getTest2(@RequestParam final String name) {
        LOGGER.info("getTest2 호출, RequestParam 값 : {}", name);

        return String.format("Hello, %s", name);
    }

    @GetMapping("/name/{name}")
    public String getTest3(@PathVariable final String name) {
        LOGGER.info("getTest3 호출, PathVariable 값 : {}", name);

        return String.format("Hello, %s", name);
    }

    @PostMapping("/member")
    public ResponseEntity<MemberDto> getMember(@RequestBody final MemberDto member) {
        LOGGER.info("getMember 호출, RequestBody 값 : {}", member.name());

        return ResponseEntity.ok(new MemberDto(String.format("Hello, %s", member.name())));
    }

    @PostMapping("/add-header")
    public ResponseEntity<MemberDto> addHeader(
            @RequestHeader("auth") final String header,
            @RequestBody final MemberDto member
    ) {
        LOGGER.info("addHeader 호출, header 값 : {}, RequestBody 값 : {}", header, member.name());

        return ResponseEntity.ok(new MemberDto(String.format("%s, %s", header, member.name())));
    }
}
