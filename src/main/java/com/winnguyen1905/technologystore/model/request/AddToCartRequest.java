package com.winnguyen1905.technologystore.model.request;

import java.util.UUID;

import com.winnguyen1905.technologystore.model.dto.BaseObjectDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddToCartRequest extends BaseObjectDTO {
    private UUID variationId;
    private UUID shopId;
    private int quantity;
}