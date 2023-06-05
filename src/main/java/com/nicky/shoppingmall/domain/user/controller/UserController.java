package com.nicky.shoppingmall.domain.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    @GetMapping("/test")
    void test() {
        log.info("Test");
    }

    @GetMapping("/test3")
    void test3() {
        log.info("Test3");
    }
}
