package com.example.backend_spring_boot_final_project.service.impl;

import com.example.backend_spring_boot_final_project.dao.CropDao;
import com.example.backend_spring_boot_final_project.dto.CropStatus;
import com.example.backend_spring_boot_final_project.dto.impl.CropDTO;
import com.example.backend_spring_boot_final_project.entity.impl.CropEntity;
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

@Service
@Transactional
public class CropServiceImpl implements CropService {

    @Autowired
    private CropDao cropDao;

    @Autowired
    private Mapping mapping;


    @Override
    public void saveCrop(CropDTO cropDTO){
        cropDTO.setCrop_code(AppUtil.generateCropId());
        CropEntity saveCrop = cropDao.save(mapping.toCropEntity(cropDTO));
        if(saveCrop == null) {
            throw new DataPersistException("Crop not saved");
        }


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
    public List<CropDTO>getAllCrops(){
        return mapping.toCropDTOList(cropDao.findAll());
    }

    @Override
    public void deleteCrop(String cropCode) {
        Optional<CropEntity> foundCrop = cropDao.findById(cropCode);
        if(foundCrop.isPresent()) {
            throw new CropNotFoundException("Crop not found");
        }else {
            cropDao.deleteById(cropCode);
        }
    }
}
