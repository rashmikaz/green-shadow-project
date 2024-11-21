package com.example.backend_spring_boot_final_project.service;

import com.example.backend_spring_boot_final_project.dto.StaffStatus;
import com.example.backend_spring_boot_final_project.dto.impl.StaffDTO;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO);

    List<StaffDTO> getAllStaff();

    StaffStatus getstaff(String staffId);

    void deleteStaff(String staffId);
}
