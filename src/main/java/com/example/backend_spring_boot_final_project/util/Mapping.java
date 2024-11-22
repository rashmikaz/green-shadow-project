package com.example.backend_spring_boot_final_project.util;


import com.example.backend_spring_boot_final_project.dto.impl.CropDTO;
import com.example.backend_spring_boot_final_project.dto.impl.FieldDTO;
import com.example.backend_spring_boot_final_project.dto.impl.StaffDTO;
import com.example.backend_spring_boot_final_project.entity.impl.CropEntity;
import com.example.backend_spring_boot_final_project.entity.impl.FieldEntity;
import com.example.backend_spring_boot_final_project.entity.impl.StaffEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;


    //for crop mapping
    public CropEntity toCropEntity(CropDTO cropDTO) {
        return modelMapper.map(cropDTO, CropEntity.class);
    }

    public CropDTO toCropDTO(CropEntity cropEntity) {
        return modelMapper.map(cropEntity, CropDTO.class);
    }

    public List<CropDTO> toCropDTOList(List<CropEntity> cropEntitiesList) {
        return modelMapper.map(cropEntitiesList,new TypeToken<List<CropDTO>>() {}.getType());
    }


    //for staff mapping
    public StaffEntity toStaffEntity(StaffDTO staffDTO) {
        return modelMapper.map(staffDTO, StaffEntity.class);
    }

    public StaffDTO toStaffDTO(StaffEntity staffEntity) {
        return modelMapper.map(staffEntity, StaffDTO.class);
    }

    public List<StaffDTO> toStaffDTOList(List<StaffEntity> staffEntitiesList) {
        return modelMapper.map(staffEntitiesList,new TypeToken<List<StaffDTO>>() {}.getType());
    }

    public List<StaffEntity> toStaffEntityList(List<StaffDTO> staffDTOList) {
        return modelMapper.map(staffDTOList,new TypeToken<List<StaffEntity>>() {}.getType());
    }


    //for field mapping
    public FieldEntity toFieldEntity(FieldDTO fieldDTO) {
        return modelMapper.map(fieldDTO, FieldEntity.class);
    }

    public FieldDTO toFieldDTO(FieldEntity fieldEntity) {
        return modelMapper.map(fieldEntity, FieldDTO.class);
    }

    public List<FieldDTO> toFieldDTOList(List<FieldEntity> fieldEntitiesList) {
        return modelMapper.map(fieldEntitiesList,new TypeToken<List<FieldDTO>>() {}.getType());
    }
    public List<FieldEntity> toFieldEntityList(List<FieldDTO> fieldDTOList) {
        return modelMapper.map(fieldDTOList,new TypeToken<List<FieldEntity>>() {}.getType());
    }



}
