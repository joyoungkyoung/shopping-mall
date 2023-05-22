package com.nicky.shoppingmall.domain.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthViewController {
    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView view = new ModelAndView("pages/login");
        return view;
    }
}
