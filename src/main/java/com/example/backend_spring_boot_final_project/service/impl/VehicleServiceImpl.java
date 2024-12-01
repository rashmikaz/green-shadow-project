package com.example.backend_spring_boot_final_project.service.impl;

import com.example.backend_spring_boot_final_project.dao.VehicleDao;
import com.example.backend_spring_boot_final_project.dto.impl.VehicleDTO;
import com.example.backend_spring_boot_final_project.entity.impl.VehicleEntity;
import com.example.backend_spring_boot_final_project.exception.DataPersistException;
import com.example.backend_spring_boot_final_project.service.VehicleService;
import com.example.backend_spring_boot_final_project.util.AppUtil;
import com.example.backend_spring_boot_final_project.util.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleDao vehicleDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void vehicleSave(VehicleDTO vehicleDTO){
           vehicleDTO.setVehicle_code(AppUtil.generateVehicleId());
           VehicleEntity saveVehicle = vehicleDao.save(mapping.toVehicleEntity(vehicleDTO));
           if (saveVehicle==null){
               throw new DataPersistException("Vehicle not saved");
           }


    }
}