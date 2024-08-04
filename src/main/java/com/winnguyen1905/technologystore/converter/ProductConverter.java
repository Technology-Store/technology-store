package com.winnguyen1905.technologystore.converter;

import java.security.InvalidAlgorithmParameterException;
import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.winnguyen1905.technologystore.common.SystemConstant;
import com.winnguyen1905.technologystore.entity.LaptopEntity;
import com.winnguyen1905.technologystore.entity.ProductEntity;
import com.winnguyen1905.technologystore.entity.SmartPhoneEntity;
import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.model.dto.LaptopDTO;
import com.winnguyen1905.technologystore.model.dto.ProductDTO;
import com.winnguyen1905.technologystore.model.dto.SmartPhoneDTO;

@Component
public class ProductConverter {

    private static Map<String, Class<?>> productRegistry;

    static {
        productRegistry = new HashMap<String, Class<?>>();
        productRegistry.put("smartphone_dto", SmartPhoneDTO.class);
        productRegistry.put("smartphone_entity", SmartPhoneEntity.class);
        
        productRegistry.put("laptop_dto", LaptopDTO.class);
        productRegistry.put("laptop_entity", LaptopEntity.class);

        // productRegistry.put("smartwatch_dto", SmartPhoneDTO.class);
        // productRegistry.put("smartwatch_entity", SmartPhoneEntity.class);
    }

    @Autowired
    private ModelMapper modelMapper;

    public <D> D toProductEntity(ProductDTO productDTO) {
        try {
            Class<?> dClass = this.productRegistry.get(productDTO.getProductType() + "_entity");
            if(dClass == null) throw new CustomRuntimeException("Not found product type " + productDTO.getProductType());
            D instanceOfDClass = (D) dClass.getDeclaredConstructor().newInstance();
            this.modelMapper.map(productDTO, instanceOfDClass);
            return instanceOfDClass;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (D) this.modelMapper.map(productDTO, ProductEntity.class);
    }

    public <D> D toProductDTO(ProductEntity product) {
        try {
            Class<?> dClass = this.productRegistry.get(product.getProductType() + "_dto");
            if(dClass == null) throw new CustomRuntimeException("Not found product type " + product.getProductType());
            D instanceOfDClass = (D) dClass.getDeclaredConstructor().newInstance();
            modelMapper.map(product, instanceOfDClass);
            return instanceOfDClass;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (D) this.modelMapper.map(product, ProductDTO.class);
    }
}