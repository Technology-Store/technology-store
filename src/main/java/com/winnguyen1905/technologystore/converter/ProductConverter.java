package com.winnguyen1905.technologystore.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.winnguyen1905.technologystore.common.ProductTypeConstant;
import com.winnguyen1905.technologystore.entity.ProductEntity;
import com.winnguyen1905.technologystore.entity.SmartPhoneEntity;
import com.winnguyen1905.technologystore.model.dto.ProductDTO;
import com.winnguyen1905.technologystore.model.dto.SmartPhoneDTO;

@Component
public class ProductConverter {

    @Autowired
    private ModelMapper modelMapper;

    public ProductEntity toProductEntity(ProductDTO productDTO) {
        switch (productDTO.getProductType().toUpperCase()) {
            case ProductTypeConstant.SMARTPHONE:
                SmartPhoneEntity smartPhoneEntity = modelMapper.map(productDTO, SmartPhoneEntity.class);
                return smartPhoneEntity;
            case ProductTypeConstant.LAPTOP:
                return null;
            case ProductTypeConstant.SMARTWATCH:
                return null;
            default:
                return null;
        }
    }

    public ProductDTO toProductDTO(ProductEntity productEntity) {
        switch (productEntity.getProductType().toUpperCase()) {
            case ProductTypeConstant.SMARTPHONE:
                SmartPhoneDTO smartPhoneDTO = modelMapper.map(productEntity, SmartPhoneDTO.class);
                return smartPhoneDTO;
            case ProductTypeConstant.LAPTOP:
                break;
            case ProductTypeConstant.SMARTWATCH:
                break;
            default:
                break;
        }
        return null;
    }
}