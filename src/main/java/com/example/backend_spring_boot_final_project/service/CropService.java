package com.example.backend_spring_boot_final_project.service;

import com.example.backend_spring_boot_final_project.dto.CropStatus;
import com.example.backend_spring_boot_final_project.dto.impl.CropDTO;

import java.util.List;

public interface CropService {
    void saveCrop(CropDTO cropDTO);


    CropStatus getCrop(String cropCode);

    List<CropDTO> getAllCrops();

    void deleteCrop(String cropCode);
}
