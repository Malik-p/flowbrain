package com.flowbrain.backend.notification.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.flowbrain.backend.common.dto.ApiResponse;
import com.flowbrain.backend.notification.dto.NotificationResponse;
import com.flowbrain.backend.notification.service.NotificationService;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(
            NotificationService notificationService) {

        this.notificationService = notificationService;
    }

    // ================= GET ALL =================

    @GetMapping
    public ResponseEntity<ApiResponse<List<NotificationResponse>>> getMyNotifications() {

        List<NotificationResponse> response = notificationService.getMyNotifications();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Notifications fetched successfully",
                        response));
    }

    // ================= UNREAD COUNT =================

    @GetMapping("/unread-count")
    public ResponseEntity<ApiResponse<Long>> getUnreadCount() {

        long count = notificationService.getUnreadCount();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Unread notification count",
                        count));
    }

    // ================= MARK AS READ =================

    @PutMapping("/{notificationId}/read")
    public ResponseEntity<ApiResponse<String>> markAsRead(

            @PathVariable String notificationId) {

        notificationService.markAsRead(notificationId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Notification marked as read",
                        "Success"));
    }

}