package com.flowbrain.backend.notification.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.flowbrain.backend.notification.entity.Notification;

public interface NotificationRepository
        extends MongoRepository<Notification, String> {

    List<Notification> findByReceiverIdOrderByCreatedAtDesc(
            String receiverId);

    long countByReceiverIdAndReadFalse(
            String receiverId);

}