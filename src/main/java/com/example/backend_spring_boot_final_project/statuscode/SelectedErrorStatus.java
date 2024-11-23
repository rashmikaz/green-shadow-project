package com.example.backend_spring_boot_final_project.statuscode;

import com.example.backend_spring_boot_final_project.dto.CropStatus;
import com.example.backend_spring_boot_final_project.dto.FieldStatus;
import com.example.backend_spring_boot_final_project.dto.StaffStatus;
import com.example.backend_spring_boot_final_project.dto.VehicleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedErrorStatus implements CropStatus, StaffStatus, FieldStatus, VehicleStatus {
    private int statusCode;
    private String statusMessage;
}
