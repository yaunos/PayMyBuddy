package com.paymybuddy.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class exampleController {
    @GetMapping("/example")
    public String example(Model model) {
        model.addAttribute("title", "hello");
        return "example";
    }
}

