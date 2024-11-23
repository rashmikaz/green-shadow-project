package com.example.backend_spring_boot_final_project.dto.impl;

import com.example.backend_spring_boot_final_project.dto.EquipmentStatus;
import com.example.backend_spring_boot_final_project.entity.EquipmentType;
import com.example.backend_spring_boot_final_project.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO implements EquipmentStatus {
    private String equipment_id;
    private String name;
    private EquipmentType type;
    private Status status;
//    private StaffDTO assigned_staff;
//    private FieldDTO assigned_field;
}
