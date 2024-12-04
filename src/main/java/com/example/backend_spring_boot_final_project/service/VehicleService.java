package com.example.backend_spring_boot_final_project.service;

import com.example.backend_spring_boot_final_project.dto.impl.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void vehicleSave(VehicleDTO vehicleDTO);

    List<VehicleDTO> getAllVehicles();
}
