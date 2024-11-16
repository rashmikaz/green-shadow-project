package com.example.backend_spring_boot_final_project.service;

import com.example.backend_spring_boot_final_project.dto.CropStatus;
import com.example.backend_spring_boot_final_project.dto.impl.CropDTO;

public interface CropService {
    void saveCrop(CropDTO cropDTO);


    CropStatus getCrop(String cropCode);
}
