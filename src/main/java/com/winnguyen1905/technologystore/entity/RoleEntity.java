package com.winnguyen1905.technologystore.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.winnguyen1905.technologystore.entity.UserEntity;
import com.winnguyen1905.technologystore.entity.base.BaseEntityAudit;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntityAudit {
    @Column(name="name", nullable = false)
    private String name;

    @Column(name="code", unique = true, nullable = false)
    private String code;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "role_detail",
        joinColumns = @JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<PermissionEntity> permissions = new HashSet<>();

    @OneToMany(mappedBy = "role")
    private Set<UserEntity> users = new HashSet<>();
}