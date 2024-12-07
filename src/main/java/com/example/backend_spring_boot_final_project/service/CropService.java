package com.example.backend_spring_boot_final_project.service;

import com.example.backend_spring_boot_final_project.dto.CropStatus;
import com.example.backend_spring_boot_final_project.dto.impl.CropDTO;
import com.example.backend_spring_boot_final_project.entity.impl.CropEntity;

import java.util.List;
import java.util.Optional;

public interface CropService {
    void saveCrop(CropDTO cropDTO);


    CropStatus getCrop(String cropCode);

    List<CropDTO> getAllCrops();

    void deleteCrop(String cropCode);

    void updateCrop(String cropCode, CropDTO cropDTO);

    List<String> getAllCropNames();

    Optional<CropEntity> findByCommonName(String commonName);

    List<CropDTO> getCropListByName(List<String> crops);
}
