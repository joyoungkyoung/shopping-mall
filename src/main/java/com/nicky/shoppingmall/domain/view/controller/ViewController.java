package com.nicky.shoppingmall.domain.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ViewController {
    
    @GetMapping("/")
    private ModelAndView home() {
        return new ModelAndView("pages/home");
    }

    @GetMapping("/login")
    private ModelAndView login() {
        return new ModelAndView("pages/login");
    }

    @GetMapping("/signup")
    private ModelAndView signup() {
        return new ModelAndView("pages/signup");
    }

}
