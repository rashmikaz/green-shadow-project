package com.example.backend_spring_boot_final_project.controller;

import com.example.backend_spring_boot_final_project.dto.impl.StaffDTO;
import com.example.backend_spring_boot_final_project.exception.DataPersistException;
import com.example.backend_spring_boot_final_project.service.StaffService;
import com.example.backend_spring_boot_final_project.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/staff")
//@CrossOrigin
public class StaffController {

   @Autowired
    private StaffService staffService;

    private static Logger logger = LoggerFactory.getLogger(StaffController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveStaff(@RequestBody StaffDTO staffDTO){

//        try {
//             staffService.saveStaff(staffDTO);
//            logger.info("staff saved");
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        }catch (DataPersistException e){
//            e.printStackTrace();
//            logger.warn("Returning Http 400 Bad Request",e.getMessage());
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }catch (Exception e){
//            e.printStackTrace();
//            logger.error("Staff save failed",e.getMessage());
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        try {
            staffService.saveStaff(staffDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

}
