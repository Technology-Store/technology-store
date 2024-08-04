package com.winnguyen1905.technologystore.controller;

import org.springframework.web.bind.annotation.RestController;

import com.winnguyen1905.technologystore.model.dto.DistrictDTO;
import com.winnguyen1905.technologystore.service.IDistrictService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("${release.api.prefix}/districts")
public class DistrictController {
    @Autowired
    private IDistrictService districtService;

    @GetMapping("/")
    public ResponseEntity<List<DistrictDTO>> getAll() {
        return ResponseEntity.ok().body(districtService.handleGetAllDistrict());
    }
}