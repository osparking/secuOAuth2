package com.bumsoap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecuredController {
    @GetMapping("secured")
    public String securePage() {
        return "secured.html";
    }
}
