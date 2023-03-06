package com.jpcchaves.finances.controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/tests")
@RestController
public class TestController {

    @GetMapping
    @RolesAllowed("ADMIN")
    public String hello() {
        return "Hello World";
    }

}
