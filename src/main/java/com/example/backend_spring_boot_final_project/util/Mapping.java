package com.example.backend_spring_boot_final_project.util;


import com.example.backend_spring_boot_final_project.dto.impl.CropDTO;
import com.example.backend_spring_boot_final_project.entity.impl.CropEntity;
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




}
