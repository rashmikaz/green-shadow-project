package com.example.backend_spring_boot_final_project.service;

import com.example.backend_spring_boot_final_project.dto.impl.StaffDTO;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO);

    List<StaffDTO> getAllStaff();
}