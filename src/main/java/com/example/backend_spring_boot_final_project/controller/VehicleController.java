package com.example.backend_spring_boot_final_project.controller;

import com.example.backend_spring_boot_final_project.dto.impl.CropDTO;
import com.example.backend_spring_boot_final_project.dto.impl.StaffDTO;
import com.example.backend_spring_boot_final_project.dto.impl.VehicleDTO;
import com.example.backend_spring_boot_final_project.entity.impl.VehicleEntity;
import com.example.backend_spring_boot_final_project.exception.DataPersistException;
import com.example.backend_spring_boot_final_project.exception.VehicleNotFoundException;
import com.example.backend_spring_boot_final_project.service.StaffService;
import com.example.backend_spring_boot_final_project.service.VehicleService;
import com.example.backend_spring_boot_final_project.util.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private StaffService staffService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>saveVehicle(@RequestBody VehicleDTO vehicleDTO) {

        try {
            StaffDTO staff = staffService.getStaffByName(vehicleDTO.getAssigned_staff().getFirstName());
            vehicleDTO.setAssigned_staff(staff);
            vehicleService.vehicleSave(vehicleDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{vehicleCode}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable("vehicleCode") String vehicleCode) {
        try {
            if(!Regex.vehicleCodeMatcher(vehicleCode)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            vehicleService.deleteVehicle(vehicleCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (VehicleNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleDTO> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/getvehiclecode/{licenseNumber}")
    public ResponseEntity<String> getVehicleCode(@PathVariable("licenseNumber") String licenseNumber) {
        try {
            Optional<VehicleEntity> vehicleEntity = vehicleService.findByLicenseNumber(licenseNumber);
            return ResponseEntity.ok(vehicleEntity.get().getVehicle_code());
        }catch (VehicleNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping(value = "/{vehicleCode}")
    public ResponseEntity<Void> updateVehicle(@PathVariable ("vehicleCode") String vehicleCode,
                                              @RequestBody VehicleDTO vehicleDTO) {

        try {
            if(!Regex.vehicleCodeMatcher(vehicleCode) || vehicleDTO == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            StaffDTO staff = staffService.getStaffByName(vehicleDTO.getAssigned_staff().getFirstName());
            vehicleDTO.setAssigned_staff(staff);
            vehicleService.updateVehicle(vehicleCode, vehicleDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (VehicleNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
