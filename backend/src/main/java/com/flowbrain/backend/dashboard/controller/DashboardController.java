package com.flowbrain.backend.dashboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flowbrain.backend.common.dto.ApiResponse;
import com.flowbrain.backend.dashboard.dto.DashboardResponse;
import com.flowbrain.backend.dashboard.service.DashboardService;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(
            DashboardService dashboardService) {

        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<DashboardResponse>> getDashboard() {

        DashboardResponse response = dashboardService.getDashboard();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Dashboard loaded successfully",
                        response));
    }

}

