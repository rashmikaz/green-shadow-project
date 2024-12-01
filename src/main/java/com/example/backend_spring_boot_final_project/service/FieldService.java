package com.example.backend_spring_boot_final_project.service;

import com.example.backend_spring_boot_final_project.dto.FieldStatus;
import com.example.backend_spring_boot_final_project.dto.impl.FieldDTO;

import java.util.List;

public interface FieldService {
    void saveField(FieldDTO fieldDTO);

    List<FieldDTO> getAllField();

    FieldStatus getField(String fieldId);

    void deleteField(String fieldId);

    FieldDTO getFieldByName(String field_name);

    List<FieldDTO> getFieldListByName(List<String> field_name);
}
