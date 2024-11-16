package com.example.backend_spring_boot_final_project.controller;

import com.example.backend_spring_boot_final_project.dto.CropStatus;
import com.example.backend_spring_boot_final_project.dto.impl.CropDTO;
import com.example.backend_spring_boot_final_project.dto.impl.FieldDTO;
import com.example.backend_spring_boot_final_project.exception.DataPersistException;
import com.example.backend_spring_boot_final_project.service.CropService;
import com.example.backend_spring_boot_final_project.statuscode.SelectedErrorStatus;
import com.example.backend_spring_boot_final_project.util.AppUtil;
import com.example.backend_spring_boot_final_project.util.Regex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("api/v1/crop")
public class CropController {

    private static Logger logger = LoggerFactory.getLogger(CropController.class);


    @Autowired
    private CropService cropService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>saveCrop(@RequestPart("common_name") String commonName,
                                        @RequestPart("scientific_name") String scientificName,
//                                        @RequestPart ("crop_image") MultipartFile cropImage,
                                        @RequestPart ("category") String category,
                                        @RequestPart ("season") String season
//                                        @RequestPart ("field") FieldDTO fieldDTO


    ){
        String base64CropImage = "";

        try {
//            byte[] bytesCropImage = cropImage.getBytes();
//            base64CropImage = AppUtil.cropImageToBase64(bytesCropImage);

//            generate crop id
            String crop_code = AppUtil.generateCropId();

            CropDTO buildCropDTO = new CropDTO();

            buildCropDTO.setCrop_code(crop_code);
            buildCropDTO.setCommon_name(commonName);
            buildCropDTO.setScientific_name(scientificName);
//            buildCropDTO.setCrop_image(base64CropImage);
            buildCropDTO.setCategory(category);
            buildCropDTO.setSeason(season);
//            buildCropDTO.setField(fieldDTO);

            cropService.saveCrop(buildCropDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/{cropCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CropStatus getSelectedCrop(@PathVariable("crop_code") String crop_code){

        if (!Regex.cropCodeMatcher(crop_code)){
            logger.error("Crop code is not valid get crop");
            return new SelectedErrorStatus(1,"Crop code is invalid");
        }

        return cropService.getCrop(crop_code);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDTO>getAllCrops(){
        return cropService.getAllCrops();
    }
}
