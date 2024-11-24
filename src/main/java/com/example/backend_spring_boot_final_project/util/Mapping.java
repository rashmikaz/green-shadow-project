package com.example.backend_spring_boot_final_project.util;


import com.example.backend_spring_boot_final_project.dto.impl.*;
import com.example.backend_spring_boot_final_project.entity.impl.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;


    //for crop mapping
    public CropEntity toCropEntity(CropDTO cropDTO) {
        return modelMapper.map(cropDTO, CropEntity.class);
    }

    public CropDTO toCropDTO(CropEntity cropEntity) {
        return modelMapper.map(cropEntity, CropDTO.class);
    }

    public List<CropDTO> toCropDTOList(List<CropEntity> cropEntitiesList) {
        return modelMapper.map(cropEntitiesList,new TypeToken<List<CropDTO>>() {}.getType());
    }


    //for staff mapping
    public StaffEntity toStaffEntity(StaffDTO staffDTO) {
        return modelMapper.map(staffDTO, StaffEntity.class);
    }

    public StaffDTO toStaffDTO(StaffEntity staffEntity) {
        return modelMapper.map(staffEntity, StaffDTO.class);
    }

    public List<StaffDTO> toStaffDTOList(List<StaffEntity> staffEntitiesList) {
        return modelMapper.map(staffEntitiesList,new TypeToken<List<StaffDTO>>() {}.getType());
    }

    public List<StaffEntity> toStaffEntityList(List<StaffDTO> staffDTOList) {
        return modelMapper.map(staffDTOList,new TypeToken<List<StaffEntity>>() {}.getType());
    }


    //for field mapping
    public FieldEntity toFieldEntity(FieldDTO fieldDTO) {
        return modelMapper.map(fieldDTO, FieldEntity.class);
    }

    public FieldDTO toFieldDTO(FieldEntity fieldEntity) {
        return modelMapper.map(fieldEntity, FieldDTO.class);
    }

    public List<FieldDTO> toFieldDTOList(List<FieldEntity> fieldEntitiesList) {
        return modelMapper.map(fieldEntitiesList,new TypeToken<List<FieldDTO>>() {}.getType());
    }
    public List<FieldEntity> toFieldEntityList(List<FieldDTO> fieldDTOList) {
        return modelMapper.map(fieldDTOList,new TypeToken<List<FieldEntity>>() {}.getType());
    }

    //for vehicle mapping
    public VehicleEntity toVehicleEntity(VehicleDTO vehicleDTO) {
        return modelMapper.map(vehicleDTO, VehicleEntity.class);
    }

    public VehicleDTO toVehicleDTO(VehicleEntity vehicleEntity) {
        return modelMapper.map(vehicleEntity, VehicleDTO.class);
    }

    public List<VehicleDTO> toVehicleDTOList(List<VehicleEntity> vehicleEntitiesList) {
        return modelMapper.map(vehicleEntitiesList,new TypeToken<List<VehicleDTO>>() {}.getType());
    }

    public List<VehicleEntity> toVehicleEntityList(List<VehicleDTO> vehicleDTOList){
        return modelMapper.map(vehicleDTOList,new TypeToken<List<VehicleEntity>>() {}.getType());
    }

    //for equipment mapping
    public EquipmentEntity toEquipmentEntity(EquipmentDTO equipmentDTO) {
        return modelMapper.map(equipmentDTO, EquipmentEntity.class);
    }
    public EquipmentDTO toEquipmentDTO(EquipmentEntity equipmentEntity) {
        return modelMapper.map(equipmentEntity, EquipmentDTO.class);
    }

    public List<EquipmentDTO> toEquipmentDTOList(List<EquipmentEntity> equipmentEntitiesList) {
        return modelMapper.map(equipmentEntitiesList,new TypeToken<List<EquipmentDTO>>() {}.getType());
    }

    //for user mapping
    public UserEntity toUserEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }

    public UserDTO toUserDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }

    public List<UserDTO> toUserDTOList(List<UserEntity> userEntitiesList) {
        return modelMapper.map(userEntitiesList,new TypeToken<List<UserDTO>>() {}.getType());
    }


}
