package com.winnguyen1905.technologystore.model.dto;

import com.winnguyen1905.technologystore.common.NotificationType;

import lombok.*;

@Setter
@Getter
public class NotificationDTO extends BaseObjectDTO<NotificationDTO> {
    private NotificationType notificationType;

    private UserDTO receiver; 

    private UserDTO sender;

    private String desctiption;
}