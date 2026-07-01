package com.flowbrain.backend.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.time.LocalDateTime;

@RestController
public class HealthController {

    @GetMapping("/health")
    public Map<String, Object> health() {

        return Map.of(
                "success", true,
                "message", "FlowBrain Backend Running Successfully",
                "version", "1.0.0",
                "timestamp", LocalDateTime.now());
    }

}