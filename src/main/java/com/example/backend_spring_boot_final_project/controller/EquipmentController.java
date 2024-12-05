package com.example.backend_spring_boot_final_project.controller;

import com.example.backend_spring_boot_final_project.dto.EquipmentStatus;
import com.example.backend_spring_boot_final_project.dto.impl.EquipmentDTO;
import com.example.backend_spring_boot_final_project.dto.impl.FieldDTO;
import com.example.backend_spring_boot_final_project.dto.impl.StaffDTO;
import com.example.backend_spring_boot_final_project.dto.impl.VehicleDTO;
import com.example.backend_spring_boot_final_project.exception.DataPersistException;
import com.example.backend_spring_boot_final_project.service.EquipmentService;
import com.example.backend_spring_boot_final_project.service.FieldService;
import com.example.backend_spring_boot_final_project.service.StaffService;
import com.example.backend_spring_boot_final_project.statuscode.SelectedErrorStatus;
import com.example.backend_spring_boot_final_project.util.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveEquipment(@RequestBody EquipmentDTO equipmentDTO){

        try {
          StaffDTO staff = staffService.getStaffByName(equipmentDTO.getAssigned_staff().getFirstName());
          FieldDTO fieldDTO = fieldService.getFieldByName(equipmentDTO.getAssigned_field().getField_name());

          equipmentDTO.setAssigned_staff(staff);
          equipmentDTO.setAssigned_field(fieldDTO);

          equipmentService.saveEquipment(equipmentDTO);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{equipmentId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public EquipmentStatus getSelectedEquipment(@PathVariable ("equipmentId") String equipmentId) {
        if (!Regex.equipIdMatcher(equipmentId)) {
            return new SelectedErrorStatus(1, "Equipment id does not match");
        }
        return equipmentService.getEquipment(equipmentId);
    }
}
