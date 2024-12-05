package com.example.backend_spring_boot_final_project.controller;

import com.example.backend_spring_boot_final_project.dto.CropStatus;
import com.example.backend_spring_boot_final_project.dto.impl.CropDTO;
import com.example.backend_spring_boot_final_project.dto.impl.FieldDTO;
import com.example.backend_spring_boot_final_project.entity.impl.CropEntity;
import com.example.backend_spring_boot_final_project.exception.CropNotFoundException;
import com.example.backend_spring_boot_final_project.exception.DataPersistException;
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


import java.util.List;



@RestController
@RequestMapping("api/v1/crop")
@CrossOrigin(origins = "http://localhost:63342")
public class CropController {


    @Autowired
    private CropService cropService;

    @Autowired
    private FieldService fieldService;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCrop(@RequestParam("common_name") String commonName,
                                         @RequestParam("scientific_name") String scientificName,
                                         @RequestParam("crop_image") MultipartFile cropImage,
                                         @RequestParam("category") String category,
                                         @RequestParam("season") String season,
                                         @RequestParam("field") String fieldDTO


    ) {
        String base64CropImage = "";

        try {

            FieldDTO field = fieldService.getFieldByName(fieldDTO);


            byte[] bytesCropImage = cropImage.getBytes();
            base64CropImage = AppUtil.cropImageToBase64(bytesCropImage);


            String crop_code = AppUtil.generateCropId();

            CropDTO buildCropDTO = new CropDTO();

            buildCropDTO.setCrop_code(crop_code);
            buildCropDTO.setCommon_name(commonName);
            buildCropDTO.setScientific_name(scientificName);
            buildCropDTO.setCrop_image(base64CropImage);
            buildCropDTO.setCategory(category);
            buildCropDTO.setSeason(season);
            buildCropDTO.setField(field);

            cropService.saveCrop(buildCropDTO);

            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDTO> getAllCrops() {
        return cropService.getAllCrops();
    }


    @GetMapping(value = "/{crop_Code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CropStatus getSelectedCrop(@PathVariable("crop_Code") String crop_code) {

        if (!Regex.cropCodeMatcher(crop_code)) {
            return new SelectedErrorStatus(1, "Crop code is invalid");
        }

        return cropService.getCrop(crop_code);
    }


    @DeleteMapping(value = "/{cropCode}")
    public ResponseEntity<Void> deleteCrop(@PathVariable("cropCode") String crop_code) {
        try {
            if (!Regex.cropCodeMatcher(crop_code)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            cropService.deleteCrop(crop_code);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CropNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{commonName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CropEntity> updateCrop(@PathVariable @RequestParam("common_name") String commonName,
                                                 @RequestParam("scientific_name") String scientificName,
                                                 @RequestPart("crop_image") MultipartFile cropImage,
                                                 @RequestParam("category") String category,
                                                 @RequestParam("season") String season,
                                                 @RequestParam("field_name") String field_name

    ) {
        String base64CropImage = "";

        try {
            FieldDTO field = fieldService.getFieldByName(field_name);
            byte[] bytesCropImage = cropImage.getBytes();
            base64CropImage = AppUtil.cropImageToBase64(bytesCropImage);

            String crop_code = AppUtil.generateCropId();

            CropDTO cropDTO = new CropDTO();
            cropDTO.setCrop_code(crop_code);
            cropDTO.setCommon_name(commonName);
            cropDTO.setScientific_name(scientificName);
            cropDTO.setCrop_image(base64CropImage);
            cropDTO.setCategory(category);
            cropDTO.setSeason(season);
            cropDTO.setField(field);

            cropService.updateCrop(commonName, cropDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "getallcropnames",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getAllCropName(){
        List<String> cropNames = cropService.getAllCropNames();
        return ResponseEntity.ok(cropNames);
    }
}
