package com.ess.essserver.app.auth;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secure")
public class SecureController {

    @GetMapping("/data")
    @PreAuthorize("hasRole('USER')") // Secures endpoint by role
    public String secureData() {
        return "This is a secure endpoint";
    }
}