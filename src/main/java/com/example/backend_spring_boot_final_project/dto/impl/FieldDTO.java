package com.example.backend_spring_boot_final_project.dto.impl;

import com.example.backend_spring_boot_final_project.dto.FieldStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDTO implements FieldStatus {
    private String field_code;
    private String field_name;
    private Point location;
    private Double extent_size;
    private String field_image1;
    private String field_image2;
//    private List<CropDTO> crops = new ArrayList<>();
//    private List<StaffDTO> allocated_staff = new ArrayList<>();
}
