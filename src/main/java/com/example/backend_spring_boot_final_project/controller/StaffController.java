package com.example.backend_spring_boot_final_project.controller;

import com.example.backend_spring_boot_final_project.dao.StaffDao;
import com.example.backend_spring_boot_final_project.dto.CropStatus;
import com.example.backend_spring_boot_final_project.dto.StaffStatus;
import com.example.backend_spring_boot_final_project.dto.impl.FieldDTO;
import com.example.backend_spring_boot_final_project.dto.impl.StaffDTO;
import com.example.backend_spring_boot_final_project.entity.impl.StaffEntity;
import com.example.backend_spring_boot_final_project.exception.CropNotFoundException;
import com.example.backend_spring_boot_final_project.exception.DataPersistException;
import com.example.backend_spring_boot_final_project.exception.StaffNotFoundException;
import com.example.backend_spring_boot_final_project.service.FieldService;
import com.example.backend_spring_boot_final_project.service.StaffService;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/staff")
@CrossOrigin
public class StaffController {

   @Autowired
    private StaffService staffService;

    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveStaff(@RequestBody StaffDTO staffDTO){

        try {
            List<String>field_name = staffDTO.getFields().stream().map(FieldDTO::getField_name).collect(Collectors.toList());
            List<FieldDTO> fields = fieldService.getFieldListByName(field_name);
            staffDTO.setFields(fields);
            staffService.saveStaff(staffDTO);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDTO>getAllStaff(){
        return staffService.getAllStaff();
    }


//    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
//    public StaffStatus getSelectedStaff(@PathVariable("id") String staffId){
//
//        if (!Regex.staffIdMatcher(staffId)){
//            return new SelectedErrorStatus(1,"id not valid");
//        }
//
//        return staffService.getstaff(staffId);
//    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void>deleteStaff(@PathVariable("id") String staffId){
        try {
            if(!Regex.staffIdMatcher(staffId)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            staffService.deleteStaff(staffId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (StaffNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{staffId}")
    public ResponseEntity<Void> updateStaff(@PathVariable ("staffId") String staffId,
                                            @RequestBody StaffDTO staffDTO){

        try {
            if(!Regex.staffIdMatcher(staffId) || staffDTO == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            List<String> field_name = staffDTO.getFields()
                    .stream()
                    .map(FieldDTO::getField_name)
                    .collect(Collectors.toList());
            List<FieldDTO> fields = fieldService.getFieldListByName(field_name);
            staffDTO.setFields(fields);
            staffService.updateStaff(staffId, staffDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (StaffNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "getallstaffnames",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getAllStaffName(){
        List<String> staffNames = staffService.getAllStaffNames();
        return ResponseEntity.ok(staffNames);
    }


    @GetMapping( "/getstaffid/{firstName}")
    public ResponseEntity<String> getStaffId(@PathVariable("firstName") String firstName){
        try {
            Optional<StaffEntity> staffEntity = staffService.findByFirstName(firstName);
            return ResponseEntity.ok(staffEntity.get().getId());
        }catch (StaffNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
