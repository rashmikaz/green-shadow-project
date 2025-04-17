package com.example.backend_spring_boot_final_project.dto.impl;

import com.example.backend_spring_boot_final_project.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO {
    private String vehicle_code;
    private String fuelType;
    private String licensePlateNumber;
    private String remarks;
    private Status status;
    private String vehicleCategory;
    private StaffDTO assigned_staff;
}
