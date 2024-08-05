package com.winnguyen1905.technologystore.model.dto;
import java.time.Instant;
import java.util.List;
import java.util.Set;

import com.winnguyen1905.technologystore.common.DiscountAppliesType;
import com.winnguyen1905.technologystore.common.DiscountType;
import com.winnguyen1905.technologystore.entity.ShopEntity;
import com.winnguyen1905.technologystore.util.annotation.FutureInstant;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
public class DiscountDTO extends AbstractDTO<DiscountDTO> {
    private String name;

    private String description;

    private DiscountType discountType;

    private Number value;

    @NotBlank
    private String code;

    @FutureInstant
    private Instant startDate;

    @FutureInstant
    private Instant endDate;

    private Integer maxUses;
    
    private Integer usesCount;

    private List<UserDTO> users;

    private Integer maxUsesPerUser;

    private Number minOrderValue;

    private Boolean isActive;

    private DiscountAppliesType appliesTo;

    private Set<ProductDTO> products;
}