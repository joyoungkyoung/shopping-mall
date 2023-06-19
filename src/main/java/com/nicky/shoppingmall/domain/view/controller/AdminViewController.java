package com.nicky.shoppingmall.domain.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminViewController {
    @GetMapping("/")
    private String adminHome() {
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/dashboard")
    private ModelAndView adminDashboard() {
        return new ModelAndView("pages/admin/dashboard");
    }
}
