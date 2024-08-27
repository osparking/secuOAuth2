package com.bumsoap.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecuredController {
    @GetMapping("secured")
    public String securePage(Authentication authentication) {
        if (authentication instanceof UsernamePasswordAuthenticationToken userPassToken) {
            System.out.println(userPassToken);
        } else if (authentication instanceof OAuth2AuthenticationToken oAuth2Token) {
            System.out.println(oAuth2Token);
        }
        return "secured.html";
    }
}
