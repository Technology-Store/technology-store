package com.winnguyen1905.technologystore.service.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.winnguyen1905.technologystore.entity.DistrictEntity;
import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.model.dto.DistrictDTO;
import com.winnguyen1905.technologystore.repository.DistrictRepository;
import com.winnguyen1905.technologystore.service.IDistrictService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DistrictService implements IDistrictService {
    
    private final DistrictRepository districtRepository;
    private final ModelMapper modelMapper;

    @Override
    public DistrictDTO handleAddDistrict(DistrictDTO districtDTO) {
        DistrictEntity district = this.modelMapper.map(districtDTO, DistrictEntity.class);
        district = this.districtRepository.save(district);
        return this.modelMapper.map(district, DistrictDTO.class);
    }

    @Override
    public DistrictDTO handleGetDistrictById(UUID id) {
        DistrictEntity district = this.districtRepository.findById(id)
                .orElseThrow(() -> new CustomRuntimeException("Not found district id " + id));
        return this.modelMapper.map(district, DistrictDTO.class);
    }

    @Override
    public List<DistrictDTO> handleGetAllDistrict() {
        List<DistrictEntity> districts = this.districtRepository.findAll();
        return districts.stream()
                .map(item -> this.modelMapper.map(item, DistrictDTO.class)).toList();
    }
    
}