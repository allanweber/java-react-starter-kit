package com.example.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "Hello Controller", description = "Sample REST controller with OpenAPI documentation")
public class HelloController {

    @GetMapping("/hello")
    @Operation(summary = "Get a greeting", description = "Returns a simple greeting message")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved greeting")
    public String hello() {
        return "Hello, World!";
    }
}