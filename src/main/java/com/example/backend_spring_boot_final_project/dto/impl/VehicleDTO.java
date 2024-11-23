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
    private String licensePlateNumber;
    private String vehicleCategory;
    private String fuelType;
    private Status status;
    private String remarks;
//    private StaffDTO assigned_staff;
}
