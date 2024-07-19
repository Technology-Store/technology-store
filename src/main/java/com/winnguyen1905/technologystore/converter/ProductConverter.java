package com.winnguyen1905.technologystore.converter;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.winnguyen1905.technologystore.entity.ProductEntity;
import com.winnguyen1905.technologystore.entity.SmartPhoneEntity;
import com.winnguyen1905.technologystore.model.dto.ProductDTO;
import com.winnguyen1905.technologystore.model.dto.SmartPhoneDTO;

@Component
public class ProductConverter {

    private static Map<String, Class<?>> productRegistry;

    static {
        productRegistry = new HashMap<String, Class<?>>();
        productRegistry.put("smartphonedto", SmartPhoneDTO.class);
        productRegistry.put("smartphoneentity", SmartPhoneEntity.class);
    }

    @Autowired
    private ModelMapper modelMapper;

    // public ProductEntity toProductEntity(ProductDTO productDTO) {
    //     switch (productDTO.getProductType()) {
    //         case ProductTypeConstant.SMARTPHONE:
    //             SmartPhoneEntity smartPhoneEntity = modelMapper.map(productDTO, SmartPhoneEntity.class);
    //             return smartPhoneEntity;
    //         case ProductTypeConstant.LAPTOP:
    //             return null;
    //         case ProductTypeConstant.SMARTWATCH:
    //             return null;
    //         default:
    //             return null;
    //     }
    // }

    public <S> Class<?> findDestinationClass(S source, String modelType) {
        Class<?> tClass = source.getClass();
        if(tClass.getSuperclass().equals(ProductDTO.class) || ProductDTO.class.isAssignableFrom(tClass)) {
            tClass = productRegistry.get(((ProductDTO) source).getProductType() + modelType);
        } else if(tClass.getSuperclass().equals(ProductEntity.class) || ProductEntity.class.isAssignableFrom(tClass)) {
            tClass = productRegistry.get(((ProductEntity) source).getProductType() + modelType);
        }
        return tClass;
    }

    @SuppressWarnings({ "unchecked" })
    public <T, S> T modelConverter(S source, String modelType) {
        try {
            Class<?> tClass = findDestinationClass(source, modelType);
            T instance = (T) tClass.getDeclaredConstructor().newInstance();
            modelMapper.map(source, instance);
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // public ProductDTO toProductDTO(ProductEntity productEntity) {
    //     switch (productEntity.getProductType()) {
    //         case ProductTypeConstant.SMARTPHONE:
    //             SmartPhoneDTO smartPhoneDTO = modelMapper.map(productEntity, SmartPhoneDTO.class);
    //             return smartPhoneDTO;
    //         case ProductTypeConstant.LAPTOP:
    //             break;
    //         case ProductTypeConstant.SMARTWATCH:
    //             break;
    //         default:
    //             break;
    //     }
    //     return null;
    // }
}