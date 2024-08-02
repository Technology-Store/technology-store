package com.winnguyen1905.technologystore.entity;

import java.util.List;

import com.winnguyen1905.technologystore.util.StringUtils;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "permission")
public class PermissionEntity extends BaseEntityAudit {
    @Column(nullable = true, name = "name")
    private String name;

    @Column(nullable = true, name = "code")
    private String code;

    @Column(nullable = true, name = "api_path")
    private String apiPath;

    @Column(nullable = true, name = "method")
    private String method;

    @Column(nullable = true, name = "module")
    private String module;

    @ManyToMany(mappedBy = "permissions", cascade = CascadeType.PERSIST)
    private List<RoleEntity> roles;

    @PrePersist
    public void prePersist() {
        String create = this.apiPath + " " + this.method + " " + this.module;
        this.code = StringUtils.convertCamelToSnake(create);
    }
}