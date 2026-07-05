package com.flowbrain.backend.notification.service;

import java.util.List;

import com.flowbrain.backend.notification.dto.NotificationResponse;
import com.flowbrain.backend.notification.enums.NotificationType;
import com.flowbrain.backend.user.entity.User;
import com.flowbrain.backend.notification.enums.NotificationType;

public interface NotificationService {

    List<NotificationResponse> getMyNotifications();

    long getUnreadCount();

    void markAsRead(String notificationId);

    void createNotification(
            User receiver,
            String title,
            String message,
            NotificationType type);

}