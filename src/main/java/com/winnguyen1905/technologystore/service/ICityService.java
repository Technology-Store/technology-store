package com.winnguyen1905.technologystore.service;

import com.winnguyen1905.technologystore.model.dto.CityDTO;
import com.winnguyen1905.technologystore.model.dto.DistrictDTO;

public interface ICityService {
    DistrictDTO handleAddCity(CityDTO cityDTO);
}