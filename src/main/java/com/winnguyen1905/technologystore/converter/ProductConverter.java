package com.winnguyen1905.technologystore.converter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.winnguyen1905.technologystore.entity.ElectronicEntity;
import com.winnguyen1905.technologystore.entity.ProductEntity;
import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.model.dto.ElectronicDTO;
import com.winnguyen1905.technologystore.model.dto.ProductDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductConverter {

    private final ModelMapper modelMapper;
    private static final Map<String, Class<?>> productRegistry;

    static {
        productRegistry = new HashMap<String, Class<?>>();
        productRegistry.put("electronic_dto", ElectronicDTO.class);
        productRegistry.put("electronic_entity", ElectronicEntity.class);
        
        // productRegistry.put("smartwatch_dto", SmartPhoneDTO.class);
        // productRegistry.put("smartwatch_entity", SmartPhoneEntity.class);
    }

    public <D> D toProductEntity(ProductDTO productDTO) {
        try {
            Class<?> dClass = this.productRegistry.get(productDTO.getProductType() + "_entity");
            if(dClass == null) throw new CustomRuntimeException("Not found product type " + productDTO.getProductType());
            D instanceOfDClass = (D) dClass.getDeclaredConstructor().newInstance();
            this.modelMapper.map(productDTO, instanceOfDClass);
            this.modelMapper.map(productDTO.getProductAttributes(), instanceOfDClass);
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
            this.modelMapper.map(product, instanceOfDClass);
            return instanceOfDClass;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (D) this.modelMapper.map(product, ProductDTO.class);
    }

    public List<Field> getAllField(Class<?> dClass) {
        if(dClass == Object.class) return new ArrayList<>();
        List<Field> parentFields = getAllField(dClass.getSuperclass());
        parentFields.addAll(Arrays.asList(dClass.getDeclaredFields()));
        return parentFields;
    }

    // public <D> D taget(D object, D target) {
    //     List<Field> fieldList = getAllField(object.getClass());
    //     fieldList.stream().forEach(item -> {

    //     });
        // field.setAccessible(true);
        // String fieldName = field.getName();
        // Object value = field.get(object);
        // if(value instanceof Collection) {
        //     for(Object val : ) 
        // } 
    // }

    // public <D> D mergeNestedProductModel(D object, D target) {
    //     List<Field> fields = getAllField(object.getClass());
    //     fields.stream().forEach(item -> {
    //         item.setAccessible(true);
    //         Object value = item.get(object);
    //         if(value instanceof Collection) {

    //         }
    //     })
    //     Field[] fields = object.getClass().getDeclaredFields();
    //     while(object.getClass().getSuperclass() != Object.class) {
    //         fieldList.addAll(Arrays.asList(object.getClass().getDeclaredFields()));
    //         object = (D) object.getClass().getSuperclass();
    //     }
    //     modelMapper.addMappings(new PropertyMap<Product, ProductDTO>() {
    //         @Override
    //         protected void configure() {
    //             map().setInventory(source.getInventory()); // Map nested Inventory
    //         }
    //     });
    //     return object;
    // }
}