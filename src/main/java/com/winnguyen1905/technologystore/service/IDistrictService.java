package com.winnguyen1905.technologystore.service;

import java.util.List;
import java.util.UUID;

import com.winnguyen1905.technologystore.model.dto.DistrictDTO;

public interface IDistrictService {
    DistrictDTO handleAddDistrict(DistrictDTO districtDTO);

    DistrictDTO handleGetDistrictById(UUID id);

    List<DistrictDTO> handleGetAllDistrict();
}