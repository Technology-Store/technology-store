package com.winnguyen1905.technologystore.service;

import com.winnguyen1905.technologystore.entity.NotificationEntity;
import com.winnguyen1905.technologystore.model.dto.NotificationDTO;

public interface INotificationService {
    NotificationEntity handlePushNotificationToSystem(NotificationDTO notificationDTO);
}