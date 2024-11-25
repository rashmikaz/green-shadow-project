package com.example.backend_spring_boot_final_project.controller;

import com.example.backend_spring_boot_final_project.dto.CropStatus;
import com.example.backend_spring_boot_final_project.dto.FieldStatus;
import com.example.backend_spring_boot_final_project.dto.impl.CropDTO;
import com.example.backend_spring_boot_final_project.dto.impl.FieldDTO;
import com.example.backend_spring_boot_final_project.dto.impl.StaffDTO;
import com.example.backend_spring_boot_final_project.exception.CropNotFoundException;
import com.example.backend_spring_boot_final_project.exception.DataPersistException;
import com.example.backend_spring_boot_final_project.exception.FieldNotFoundException;
import com.example.backend_spring_boot_final_project.service.CropService;
import com.example.backend_spring_boot_final_project.service.FieldService;
import com.example.backend_spring_boot_final_project.statuscode.SelectedErrorStatus;
import com.example.backend_spring_boot_final_project.util.AppUtil;
import com.example.backend_spring_boot_final_project.util.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/field")
public class FieldController {

    @Autowired
    private FieldService fieldService;



    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>saveField(@RequestParam("field_name")String fieldName,
                                         @RequestParam("x") int x,
                                         @RequestParam("y") int y,
                                         @RequestParam("extent_size") Double extentSize,
                                         @RequestPart("field_image_1")MultipartFile image1,
                                         @RequestPart("field_image_2")MultipartFile image2,
                                         @RequestPart (value = "crops[]",required = false) List<CropDTO> crops,
                                         @RequestPart (value = "staff[]",required = false) List<StaffDTO> staff){

        Point location = new Point(x,y);
        String base64FieldImage1 ="";
        String base64FieldImage2 ="";


        try {


            byte[] bytesFieldImage1 = image1.getBytes();
            base64FieldImage1 = AppUtil.fieldImageOneToBase64(bytesFieldImage1);

            byte[] bytesFieldImage2 = image2.getBytes();
            base64FieldImage2 = AppUtil.fieldImageTwoToBase64(bytesFieldImage2);


            String field_code = AppUtil.generateFieldId();

            FieldDTO buildFieldDTO = new FieldDTO();

            buildFieldDTO.setField_code(field_code);
            buildFieldDTO.setField_name(fieldName);
            buildFieldDTO.setLocation(location);
            buildFieldDTO.setExtent_size(extentSize);
            buildFieldDTO.setField_image1(base64FieldImage1);
            buildFieldDTO.setField_image1(base64FieldImage2);
            buildFieldDTO.setCrops(crops);
            buildFieldDTO.setAllocated_staff(staff);



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
