package com.winnguyen1905.technologystore.converter;

import java.security.InvalidAlgorithmParameterException;
import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.winnguyen1905.technologystore.common.SystemConstant;
import com.winnguyen1905.technologystore.entity.ProductEntity;
import com.winnguyen1905.technologystore.entity.SmartPhoneEntity;
import com.winnguyen1905.technologystore.model.dto.ProductDTO;
import com.winnguyen1905.technologystore.model.dto.SmartPhoneDTO;

@Component
public class ProductConverter {

    private static Map<String, Class<?>> productRegistry;

    static {
        productRegistry = new HashMap<String, Class<?>>();
        productRegistry.put("smartphone_dto", SmartPhoneDTO.class);
        productRegistry.put("smartphone_entity", SmartPhoneEntity.class);
    }

    @Autowired
    private ModelMapper modelMapper;

    public <D> D toProductEntity(ProductDTO productDTO) {
        try {
            Class<?> dClass = this.productRegistry.get(productDTO.getProductType() + "_entity");
            D instanceOfDClass = (D) dClass.getDeclaredConstructor().newInstance();
            modelMapper.map(productDTO, instanceOfDClass);
            return instanceOfDClass;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (D) this.modelMapper.map(productDTO, ProductEntity.class);
    }

    public <D> D toProductDTO(ProductEntity product) {
        try {
            Class<?> dClass = this.productRegistry.get(product.getProductType() + "_dto");
            D instanceOfDClass = (D) dClass.getDeclaredConstructor().newInstance();
            modelMapper.map(product, instanceOfDClass);
            return instanceOfDClass;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (D) this.modelMapper.map(product, ProductDTO.class);
    }
}