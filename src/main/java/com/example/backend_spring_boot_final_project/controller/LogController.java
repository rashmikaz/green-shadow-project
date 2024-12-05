package com.example.backend_spring_boot_final_project.controller;

import com.example.backend_spring_boot_final_project.dto.impl.CropDTO;
import com.example.backend_spring_boot_final_project.dto.impl.FieldDTO;
import com.example.backend_spring_boot_final_project.dto.impl.MonitoringLogDTO;
import com.example.backend_spring_boot_final_project.dto.impl.StaffDTO;
import com.example.backend_spring_boot_final_project.exception.DataPersistException;
import com.example.backend_spring_boot_final_project.service.CropService;
import com.example.backend_spring_boot_final_project.service.FieldService;
import com.example.backend_spring_boot_final_project.service.LogService;
import com.example.backend_spring_boot_final_project.service.StaffService;
import com.example.backend_spring_boot_final_project.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/log")
@CrossOrigin
public class LogController {
    @Autowired
    private LogService logService;
    @Autowired
    private FieldService fieldService;
    @Autowired
    private CropService cropService;
    @Autowired
    private StaffService staffService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveLog(@RequestParam("logDate") String logDate,
                                        @RequestParam ("logDetails") String logDetails,
                                        @RequestPart ("observedImage") MultipartFile observedImage,
                                        @RequestParam (value = "fields",required = false) List<String> fields,
                                        @RequestParam (value = "crops",required = false) List<String> crops,
                                        @RequestParam (value = "staff",required = false) List<String> staffs
    ) {
        String base64ObservedImage = "";
        try {
            List<FieldDTO> field = fieldService.getFieldListByName(fields);
            List<CropDTO> crop = cropService.getCropListByName(crops);
            List<StaffDTO> staff = staffService.getStaffListByName(staffs);
            byte[] byteObservedImage = observedImage.getBytes();
            base64ObservedImage = AppUtil.observedImageOneToBase64(byteObservedImage);

            String log_code = AppUtil.generateLogId();

            MonitoringLogDTO buildMonitoringLogDTO = new MonitoringLogDTO();
            buildMonitoringLogDTO.setLog_code(log_code);
            buildMonitoringLogDTO.setLog_date(logDate);
            buildMonitoringLogDTO.setLog_details(logDetails);
            buildMonitoringLogDTO.setObserved_image(base64ObservedImage);
            buildMonitoringLogDTO.setFields(field);
            buildMonitoringLogDTO.setCrops(crop);
            buildMonitoringLogDTO.setStaff(staff);
            logService.saveLog(buildMonitoringLogDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
