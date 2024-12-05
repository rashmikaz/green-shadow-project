package com.example.backend_spring_boot_final_project.service;

import com.example.backend_spring_boot_final_project.dto.EquipmentStatus;
import com.example.backend_spring_boot_final_project.dto.impl.EquipmentDTO;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO);

    EquipmentStatus getEquipment(String equipmentId);

    List<EquipmentDTO> getAllEquipment();

    void deleteEquipment(String equipmentId);
}
