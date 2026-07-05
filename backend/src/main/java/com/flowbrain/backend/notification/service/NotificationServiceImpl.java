package com.flowbrain.backend.notification.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.flowbrain.backend.common.CurrentUserService;
import com.flowbrain.backend.common.exception.ResourceNotFoundException;
import com.flowbrain.backend.notification.dto.NotificationResponse;
import com.flowbrain.backend.notification.entity.Notification;
import com.flowbrain.backend.notification.enums.NotificationType;
import com.flowbrain.backend.notification.repository.NotificationRepository;
import com.flowbrain.backend.user.entity.User;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final CurrentUserService currentUserService;

    public NotificationServiceImpl(
            NotificationRepository notificationRepository,
            CurrentUserService currentUserService) {

        this.notificationRepository = notificationRepository;
        this.currentUserService = currentUserService;
    }

    @Override
    public List<NotificationResponse> getMyNotifications() {

        User currentUser = currentUserService.getCurrentUser();

        return notificationRepository
                .findByReceiverIdOrderByCreatedAtDesc(currentUser.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public long getUnreadCount() {

        User currentUser = currentUserService.getCurrentUser();

        return notificationRepository.countByReceiverIdAndReadFalse(
                currentUser.getId());
    }

    @Override
    public void markAsRead(String notificationId) {

        Notification notification = notificationRepository
                .findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found"));

        notification.setRead(true);

        notificationRepository.save(notification);
    }

    // ================= HELPER =================

    public void createNotification(
            User receiver,
            String title,
            String message,
            NotificationType type) {

        Notification notification = new Notification();

        notification.setReceiver(receiver);
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setType(type);
        notification.setRead(false);
        notification.setCreatedAt(LocalDateTime.now());

        notificationRepository.save(notification);
    }

    // ================= MAPPER =================

    private NotificationResponse mapToResponse(
            Notification notification) {

        NotificationResponse response = new NotificationResponse();

        response.setId(notification.getId());
        response.setTitle(notification.getTitle());
        response.setMessage(notification.getMessage());
        response.setType(notification.getType().name());
        response.setRead(notification.isRead());
        response.setCreatedAt(notification.getCreatedAt());

        return response;
    }

}