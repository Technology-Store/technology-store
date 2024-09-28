package com.winnguyen1905.technologystore.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.winnguyen1905.technologystore.entity.base.BaseEntityAudit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ShopEntity.class, name = "shop"),
    @JsonSubTypes.Type(value = CustomerEntity.class, name = "customer"),
})
public class UserEntity extends BaseEntityAudit {
    
    @Column(name = "frist_name", nullable = true)
    private String firstName;

    @Column(name = "last_name", nullable = true)
    private String lastName;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "phone", nullable = true)
    private String phone;

    @Column(name = "type", insertable = false, updatable = false)
    private String type;

    @Column(columnDefinition = "MEDIUMTEXT", name = "refresh_token", nullable = true)
    private String refreshToken;

    @OneToMany(mappedBy = "user")
    private List<CommentEntity> MyComments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<OrderEntity> orders;

    @OneToMany(mappedBy = "sender")
    private List<NotificationEntity> sents;

    @OneToMany(mappedBy = "receiver")
    private List<NotificationEntity> receiveds;

    @PrePersist
    protected void prePersist() {
        this.status = true;
    }

}