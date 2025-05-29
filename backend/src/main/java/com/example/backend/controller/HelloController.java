package com.example.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public GreetingResponse hello() {
        double random = Math.random();
        if (random < 0.3) { // 30% chance of error
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Random server error occurred");
        }
        return new GreetingResponse("Hello, World!");
    }

    public record GreetingResponse(String message) {
    }
}