package com.example.backend_spring_boot_final_project.service;

import com.example.backend_spring_boot_final_project.dto.EquipmentStatus;
import com.example.backend_spring_boot_final_project.dto.impl.EquipmentDTO;
import com.example.backend_spring_boot_final_project.entity.impl.EquipmentEntity;

import java.util.List;
import java.util.Optional;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO);

    EquipmentStatus getEquipment(String equipmentId);

    List<EquipmentDTO> getAllEquipment();

    void deleteEquipment(String equipmentId);

    void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO);

    Optional<EquipmentEntity> findByEquipName(String equipmentName);
}
