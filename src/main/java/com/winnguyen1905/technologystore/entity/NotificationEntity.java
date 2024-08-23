package com.winnguyen1905.technologystore.entity;

import com.winnguyen1905.technologystore.common.NotificationType;
import com.winnguyen1905.technologystore.entity.base.BaseEntityAudit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "notifications")
public class NotificationEntity extends BaseEntityAudit {
    @Column(name = "noti_type")
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
}