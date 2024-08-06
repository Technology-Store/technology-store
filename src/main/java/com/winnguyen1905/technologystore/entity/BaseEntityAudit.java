package com.winnguyen1905.technologystore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.winnguyen1905.technologystore.util.SecurityUtils;

import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntityAudit extends BaseEntity implements IBaseAction {

    @Column(name = "created_by", nullable = true)
    private String createdBy;

    @Column(name = "updated_by", nullable = true)
    private String updatedBy;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private Instant createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date", updatable = true)
    private Instant updatedDate;

    @Column(name = "is_deleted", updatable = true)
    private Boolean isDeleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntityAudit)) return false;
        if (!super.equals(o)) return false;
        BaseEntityAudit that = (BaseEntityAudit) o;
        return createdBy.equals(that.createdBy) &&
                updatedBy.equals(that.updatedBy) &&
                createdDate.equals(that.createdDate) &&
                updatedDate.equals(that.updatedDate);
    }

    @Override
    public int hashCode() {
        return
            Objects.hash(super.hashCode(), createdBy, updatedBy, createdDate, updatedDate);
    }

    public String findSystemUser() {
        return SecurityUtils.getCurrentUserLogin().orElse("Unknown");
    }

    @PrePersist
    public void prePersist() {
        this.setIsDeleted(false);
        this.setCreatedBy(findSystemUser());
    }

    @PreUpdate
    public void preUpdate() {
        this.setUpdatedBy(findSystemUser());
    }

    @PreRemove
    public void preRemove() {
        // this.setUpdatedBy(findSystemUser());
    }

}