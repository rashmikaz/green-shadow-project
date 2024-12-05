package com.example.backend_spring_boot_final_project.service;

import com.example.backend_spring_boot_final_project.dto.VehicleStatus;
import com.example.backend_spring_boot_final_project.dto.impl.VehicleDTO;
import com.example.backend_spring_boot_final_project.entity.impl.VehicleEntity;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    void vehicleSave(VehicleDTO vehicleDTO);

    void deleteVehicle(String vehicleCode);

    List<VehicleDTO> getAllVehicles();


    Optional<VehicleEntity> findByLicenseNumber(String licenseNumber);

    void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO);
}
