package com.example.backend_spring_boot_final_project.service.impl;

import com.example.backend_spring_boot_final_project.dao.FieldDao;
import com.example.backend_spring_boot_final_project.dao.StaffDao;
import com.example.backend_spring_boot_final_project.dto.StaffStatus;
import com.example.backend_spring_boot_final_project.dto.impl.FieldDTO;
import com.example.backend_spring_boot_final_project.dto.impl.StaffDTO;
import com.example.backend_spring_boot_final_project.entity.impl.FieldEntity;
import com.example.backend_spring_boot_final_project.entity.impl.StaffEntity;
import com.example.backend_spring_boot_final_project.exception.DataPersistException;
import com.example.backend_spring_boot_final_project.exception.StaffNotFoundException;
import com.example.backend_spring_boot_final_project.service.StaffService;
import com.example.backend_spring_boot_final_project.statuscode.SelectedErrorStatus;
import com.example.backend_spring_boot_final_project.util.AppUtil;
import com.example.backend_spring_boot_final_project.util.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private FieldDao fieldDao;



    @Autowired
    private Mapping mapping;

    @Override
    public void saveStaff(StaffDTO staffDTO){
        staffDTO.setId(AppUtil.generateStaffId());
       StaffEntity staffEntity = staffDao.save(mapping.toStaffEntity(staffDTO));
       if (staffEntity==null){
           throw new DataPersistException("Staff not saved");
       }

    }
    @Override
    public List<StaffDTO> getAllStaff(){
        List<StaffEntity> staffs = staffDao.findAll();
        return staffs.stream()
                .map(staff -> {
                    StaffDTO staffDTO = new StaffDTO();
                    staffDTO.setFirst_name(staff.getFirst_name());
                    staffDTO.setLast_name(staff.getLast_name());
                    staffDTO.setDesignation(staff.getDesignation());
                    staffDTO.setGender(staff.getGender());
                    staffDTO.setJoined_date(staff.getJoined_date());
                    staffDTO.setDob(staff.getDob());
                    staffDTO.setAddress(staff.getAddress());
                    staffDTO.setContact_no(staff.getContact_no());
                    staffDTO.setEmail(staff.getEmail());
                    staffDTO.setRole(staff.getRole());
                    List<FieldDTO> assignedFieldDTO = new ArrayList<>();
                    for (FieldEntity field : staff.getFields()) {
                        Optional<FieldEntity> fieldOpt = fieldDao.findById(field.getField_name());
                        if (fieldOpt.isPresent()) {
                            assignedFieldDTO.add(mapping.toFieldDTO(fieldOpt.get()));
                        }
                    }
                    staffDTO.setFields(assignedFieldDTO);
                    return staffDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public StaffStatus getstaff(String staffId) {
        if (staffDao.existsById(staffId)) {
            var selectedStaff = staffDao.getReferenceById(staffId);
            return mapping.toStaffDTO(selectedStaff);
        } else {
            return new SelectedErrorStatus(2, "Selected staff id does not exist");
        }
    }

   @Override
    public void deleteStaff(String staffId){
        Optional<StaffEntity> foundStaff = staffDao.findById(staffId);
        if (!foundStaff.isPresent()){
            throw new StaffNotFoundException("Staff Member Not Found");
        }else {
            staffDao.deleteById(staffId);
        }
    }


    @Override
    public void updateStaff(String staffId, StaffDTO staffDTO) {
        Optional<StaffEntity> tmpStaff = staffDao.findById(staffId);
        if(!tmpStaff.isPresent()) {
            throw new StaffNotFoundException("Staff Member Not Found");
        }else{
            tmpStaff.get().setFirst_name(staffDTO.getFirst_name());
            tmpStaff.get().setLast_name(staffDTO.getLast_name());
            tmpStaff.get().setDesignation(staffDTO.getDesignation());
            tmpStaff.get().setGender(staffDTO.getGender());
            tmpStaff.get().setJoined_date(staffDTO.getJoined_date());
            tmpStaff.get().setDob(staffDTO.getDob());
            tmpStaff.get().setAddress(staffDTO.getAddress());
            tmpStaff.get().setContact_no(staffDTO.getContact_no());
            tmpStaff.get().setEmail(staffDTO.getEmail());
            tmpStaff.get().setRole(staffDTO.getRole());
        }
    }

    @Override
    public StaffDTO getStaffByName(String staff_name) {
        Optional<StaffEntity> tmpStaff = staffDao.findByStaffName(staff_name);
        if(!tmpStaff.isPresent()){
            throw new StaffNotFoundException("Staff not found: " + staff_name);
        }
        return mapping.toStaffDTO(tmpStaff.get());
    }

    @Override
    public List<String> getAllStaffNames() {
        List<StaffEntity> staffEntities = staffDao.findAll();
        return staffEntities.stream()
                .map(StaffEntity::getFirst_name)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StaffEntity> findByFirstName(String firstName) {
        return staffDao.findByStaffName(firstName);
    }
    @Override
    public List<StaffDTO> getStaffListByName(List<String> staffs) {
        if(staffs.isEmpty() || staffs == null){
            return Collections.emptyList();
        }

        List<StaffEntity> staffEntities = staffDao.findByStaffNameList(staffs);

        if(staffEntities.isEmpty()){
            throw new StaffNotFoundException("Staff Member Not Found");
        }

        return staffEntities.stream()
                .map(mapping::toStaffDTO)
                .collect(Collectors.toList());
    }
}
