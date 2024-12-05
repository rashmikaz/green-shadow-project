package com.example.backend_spring_boot_final_project.statuscode;

import com.example.backend_spring_boot_final_project.dto.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedErrorStatus implements CropStatus, StaffStatus, FieldStatus, VehicleStatus, EquipmentStatus,UserStatus,MonitoringLogStatus {
    private int statusCode;
    private String statusMessage;
}
