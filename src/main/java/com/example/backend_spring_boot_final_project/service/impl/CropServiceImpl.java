package com.example.backend_spring_boot_final_project.service.impl;


import com.example.backend_spring_boot_final_project.dao.CropDao;
import com.example.backend_spring_boot_final_project.dao.FieldDao;
import com.example.backend_spring_boot_final_project.dto.CropStatus;
import com.example.backend_spring_boot_final_project.dto.impl.CropDTO;
import com.example.backend_spring_boot_final_project.dto.impl.FieldDTO;
import com.example.backend_spring_boot_final_project.entity.impl.CropEntity;
import com.example.backend_spring_boot_final_project.entity.impl.FieldEntity;
import com.example.backend_spring_boot_final_project.exception.CropNotFoundException;
import com.example.backend_spring_boot_final_project.exception.DataPersistException;
import com.example.backend_spring_boot_final_project.service.CropService;
import com.example.backend_spring_boot_final_project.statuscode.SelectedErrorStatus;
import com.example.backend_spring_boot_final_project.util.AppUtil;
import com.example.backend_spring_boot_final_project.util.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CropServiceImpl implements CropService {

    @Autowired
    private CropDao cropDao;

    @Autowired
    private Mapping mapping;

    @Autowired
    private FieldDao fieldDao;


    @Override
    public void saveCrop(CropDTO cropDTO){
        cropDTO.setCrop_code(AppUtil.generateCropId());
        CropEntity saveCrop = cropDao.save(mapping.toCropEntity(cropDTO));
        if(saveCrop == null) {
            throw new DataPersistException("Crop not saved");
        }


    }

    @Override
    public List<CropDTO> getAllCrops() {
        List<CropEntity> crops = cropDao.findAll();
        return crops.stream()
                .map(crop -> {
                    CropDTO cropDTO = new CropDTO();
                    cropDTO.setCrop_image(crop.getCrop_image());
                    cropDTO.setCommon_name(crop.getCommon_name());
                    cropDTO.setScientific_name(crop.getScientific_name());
                    cropDTO.setCategory(crop.getCategory());
                    cropDTO.setSeason(crop.getSeason());
                    Optional<FieldEntity> assignedField = fieldDao.findById(crop.getField().getField_code());
                    FieldDTO assignedFieldDTO = assignedField.isPresent() ?
                            mapping.toFieldDTO(assignedField.get()) : null;
                    cropDTO.setField(assignedFieldDTO);
                    return cropDTO;
                })
                .collect(Collectors.toList());

    }


    @Override
    public CropStatus getCrop(String cropCode){
        if(cropDao.existsById(cropCode)) {
            var selectedCrop = cropDao.getReferenceById(cropCode);
            return mapping.toCropDTO(selectedCrop);
        }else {
            return new SelectedErrorStatus(2,"Selected crop does not exist");
        }
    }

    @Override
    public void deleteCrop(String cropCode) {
        Optional<CropEntity> foundCrop = cropDao.findById(cropCode);
        if(!foundCrop.isPresent()) {
            throw new CropNotFoundException("Crop not found");
        }else {
            cropDao.deleteById(cropCode);
        }
    }



   @Override
    public void updateCrop(String commonName, CropDTO cropDTO){
        Optional<CropEntity> tmpCrop = cropDao.findByCropName(commonName);
        if(!tmpCrop.isPresent()) {
            throw new CropNotFoundException("Crop not found");
        }else {
            tmpCrop.get().setCommon_name(cropDTO.getCommon_name());
            tmpCrop.get().setScientific_name(cropDTO.getScientific_name());
            tmpCrop.get().setCrop_image(cropDTO.getCrop_image());
            tmpCrop.get().setCategory(cropDTO.getCategory());
            tmpCrop.get().setSeason(cropDTO.getSeason());
            FieldEntity fieldEntity = mapping.toFieldEntity(cropDTO.getField());
            tmpCrop.get().setField(fieldEntity);
        }
    }


    @Override
    public List<String> getAllCropNames() {
        List<CropEntity> cropEntities = cropDao.findAll();
        return cropEntities.stream()
                .map(CropEntity::getCommon_name)
                .collect(Collectors.toList());
    }



}
