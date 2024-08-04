package com.menk.social_media_analytics.controller;

import com.menk.social_media_analytics.service.LinkedInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/linkedin")
public class LinkedInController {

    @Autowired
    private LinkedInService linkedInService;

    @PostMapping("/fetch")
    public String fetchLinkedInData(@AuthenticationPrincipal Authentication authentication) {
        linkedInService.fetchAndProcessLinkedInData(authentication);
        return "Fetched and processed LinkedIn Data";
    }
}
