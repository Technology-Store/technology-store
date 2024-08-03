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

    // Registry new product type here
    static {
        productRegistry = new HashMap<String, Class<?>>();
        productRegistry.put("smartphonedto", SmartPhoneDTO.class);
        productRegistry.put("smartphoneentity", SmartPhoneEntity.class);
    }

    @Autowired
    private ModelMapper modelMapper;

    public static Boolean isItOrIsChildClass(Class<?> isChildClass, Class<?> isParentClass) {
        return isChildClass.getSuperclass().equals(isParentClass) || isParentClass.isAssignableFrom(isChildClass);
    }

    public <S> Class<?> findTheDestinationClass(S source, String modelType) throws InvalidAlgorithmParameterException {
        Class<?> DClass = source.getClass();
        Boolean toDTO = modelType.equals(SystemConstant.TO_DTO), toEntity = modelType.equals(SystemConstant.TO_ENTITY);
        if(toEntity && isItOrIsChildClass(DClass, ProductDTO.class)) {
            DClass = productRegistry.get(((ProductDTO) source).getProductType() + modelType);
        } else if(toDTO && isItOrIsChildClass(DClass, ProductEntity.class)) {
            DClass = productRegistry.get(((ProductEntity) source).getProductType() + modelType);
        } else throw new InvalidAlgorithmParameterException("modelType to convert invalid " + modelType);
        return DClass;
    }

    /**
     * @param <D> Destination type 
     * @param <S> Input type
     * @param source Converted object
     * @param modelType Is model <DTO, Entity> that we want to convert to
     * @return  { 
     *      Example: 
     *          <S> ProductDTO
     *          <D> SmartPhoneEntity
     * 
     *          source ProductDTO productDTO;
     *          modelType String SystemConstant.TO_DTO ("dto") or SystemConstant.TO_Entity ("entity");
     *      
     *          Responsability: ProductDTO<SmartPhoneDTO> -> return ProductEntity<SmartPhoneEntity> 
     *          ...and vice versa
     * }
     */
    public <D, S> D modelConverter(S source, String modelType) {
        try {
            Class<?> dClass = findTheDestinationClass(source, modelType);
            D instanceOfDClass = (D) dClass.getDeclaredConstructor().newInstance();
            modelMapper.map(source, instanceOfDClass);
            return instanceOfDClass;
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
}