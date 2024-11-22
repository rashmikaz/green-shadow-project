package com.example.backend_spring_boot_final_project.controller;

import com.example.backend_spring_boot_final_project.dto.CropStatus;
import com.example.backend_spring_boot_final_project.dto.FieldStatus;
import com.example.backend_spring_boot_final_project.dto.impl.CropDTO;
import com.example.backend_spring_boot_final_project.dto.impl.FieldDTO;
import com.example.backend_spring_boot_final_project.exception.CropNotFoundException;
import com.example.backend_spring_boot_final_project.exception.DataPersistException;
import com.example.backend_spring_boot_final_project.exception.FieldNotFoundException;
import com.example.backend_spring_boot_final_project.service.FieldService;
import com.example.backend_spring_boot_final_project.statuscode.SelectedErrorStatus;
import com.example.backend_spring_boot_final_project.util.AppUtil;
import com.example.backend_spring_boot_final_project.util.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/field")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>saveField(@RequestParam("field_name")String fieldName,
                                         @RequestParam("x") int x,
                                         @RequestParam("y") int y,
                                         @RequestParam("extent_size") Double extentSize){

        Point location = new Point(x,y);

        try {
            String field_code = AppUtil.generateFieldId();

            FieldDTO buildFieldDTO = new FieldDTO();

            buildFieldDTO.setField_code(field_code);
            buildFieldDTO.setField_name(fieldName);
            buildFieldDTO.setLocation(location);
            buildFieldDTO.setExtent_size(extentSize);

            fieldService.saveField(buildFieldDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDTO> getAllField(){
        return fieldService.getAllField();
    }

    @GetMapping(value = "/{fieldCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldStatus getSelectedfield(@PathVariable("fieldCode") String field_id){

        if (!Regex.fieldCodeMatcher(field_id)){
            return new SelectedErrorStatus(1,"field is invalid");
        }

        return fieldService.getField(field_id);
    }

    @DeleteMapping(value = "/{fieldCode}")
    public ResponseEntity<Void>deleteField(@PathVariable("fieldCode") String field_id){
        try {
            if(!Regex.fieldCodeMatcher(field_id)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            fieldService.deleteField(field_id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (FieldNotFoundException e){
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
