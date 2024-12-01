package com.example.backend_spring_boot_final_project.service.impl;

import com.example.backend_spring_boot_final_project.dao.FieldDao;
import com.example.backend_spring_boot_final_project.dto.FieldStatus;
import com.example.backend_spring_boot_final_project.dto.impl.FieldDTO;
import com.example.backend_spring_boot_final_project.entity.impl.FieldEntity;
import com.example.backend_spring_boot_final_project.exception.CropNotFoundException;
import com.example.backend_spring_boot_final_project.exception.DataPersistException;
import com.example.backend_spring_boot_final_project.exception.FieldNotFoundException;
import com.example.backend_spring_boot_final_project.service.FieldService;
import com.example.backend_spring_boot_final_project.statuscode.SelectedErrorStatus;
import com.example.backend_spring_boot_final_project.util.AppUtil;
import com.example.backend_spring_boot_final_project.util.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveField(FieldDTO fieldDTO){
        fieldDTO.setField_code(AppUtil.generateFieldId());
        FieldEntity fieldEntity = fieldDao.save(mapping.toFieldEntity(fieldDTO));
        if (fieldEntity==null){
            throw new DataPersistException("field not saved");
        }


    }

    @Override
    public List<FieldDTO> getAllField(){
        return mapping.toFieldDTOList(fieldDao.findAll());
    }

    @Override
    public FieldStatus getField(String fieldId){
        if(fieldDao.existsById(fieldId)) {
            var selectedField = fieldDao.getReferenceById(fieldId);
            return mapping.toFieldDTO(selectedField);
        }else {
            return new SelectedErrorStatus(2,"Selected field does not exist");
        }
    }

    @Override
    public void deleteField(String fieldId) {
        Optional<FieldEntity> foundField = fieldDao.findById(fieldId);
        if(!foundField.isPresent()) {
            throw new CropNotFoundException("field not found");
        }else {
            fieldDao.deleteById(fieldId);
        }
    }

    @Override
    public FieldDTO getFieldByName(String field_name) {
        Optional<FieldEntity> tmpField = fieldDao.findByFieldName(field_name);
        if(!tmpField.isPresent()){
            throw new FieldNotFoundException("Field not found: " + field_name);
        }
        return mapping.toFieldDTO(tmpField.get());
    }
    @Override
    public List<FieldDTO> getFieldListByName(List<String> field_name) {
        if(field_name == null || field_name.isEmpty()){
            return Collections.emptyList();
        }

        List<FieldEntity> fieldEntities = fieldDao.findByFieldNameList(field_name);

        if(fieldEntities.isEmpty()){
            throw new FieldNotFoundException("Field not found");
        }

        return fieldEntities.stream()
                .map(mapping::toFieldDTO)
                .collect(Collectors.toList());
    }
}
