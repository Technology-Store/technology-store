package com.winnguyen1905.technologystore.entity.base;

import org.hibernate.annotations.GenericGenerator;
import org.modelmapper.internal.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -863164858986274318L;

    @Id
    @Column(columnDefinition = "BINARY(16)")
    @GeneratedValue(generator = "custom-uuid")
    @GenericGenerator(name = "custom-uuid", strategy = "com.winnguyen1905.technologystore.entity.base.CustomUUIDGenerator")
    private UUID id;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;
        BaseEntity that = (BaseEntity) o;
        return id.equals(that.id);
    }
    
}