package com.winnguyen1905.technologystore.entity;

import com.nimbusds.jose.shaded.gson.JsonObject;
import com.winnguyen1905.technologystore.common.NotificationType;
import com.winnguyen1905.technologystore.entity.base.BaseEntityAudit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "noti_receiver_id")
    private UserEntity receiver; 

    @ManyToOne
    @JoinColumn(name = "noti_sender_id")
    private UserEntity sender;

    private String content;

    // @Column(name = "noti_options")
    // private JsonObject options;
}