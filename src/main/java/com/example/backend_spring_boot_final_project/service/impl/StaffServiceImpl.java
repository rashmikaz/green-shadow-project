package com.example.backend_spring_boot_final_project.service.impl;

import com.example.backend_spring_boot_final_project.dao.StaffDao;
import com.example.backend_spring_boot_final_project.dto.StaffStatus;
import com.example.backend_spring_boot_final_project.dto.impl.StaffDTO;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffDao staffDao;



    @Autowired
    private Mapping mapping;

    @Override
    public void saveStaff(StaffDTO staffDTO){
        staffDTO.setStaffId(AppUtil.generateStaffId());
       StaffEntity staffEntity = staffDao.save(mapping.toStaffEntity(staffDTO));
       if (staffEntity==null){
           throw new DataPersistException("Staff not saved");
       }

    }
    @Override
    public List<StaffDTO> getAllStaff(){
        return mapping.toStaffDTOList(staffDao.findAll());
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
            tmpStaff.get().setFirstName(staffDTO.getFirstName());
            tmpStaff.get().setLastName(staffDTO.getLastName());
            tmpStaff.get().setDesignation(staffDTO.getDesignation());
            tmpStaff.get().setGender(staffDTO.getGender());
            tmpStaff.get().setJoinedDate(staffDTO.getJoinedDate());
            tmpStaff.get().setDob(staffDTO.getDob());
            tmpStaff.get().setAddressLine1(staffDTO.getAddressLine1());
            tmpStaff.get().setAddressLine2(staffDTO.getAddressLine2());
            tmpStaff.get().setAddressLine3(staffDTO.getAddressLine3());
            tmpStaff.get().setAddressLine4(staffDTO.getAddressLine4());
            tmpStaff.get().setAddressLine5(staffDTO.getAddressLine5());
            tmpStaff.get().setContactNo(staffDTO.getContactNo());
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
                .map(StaffEntity::getFirstName)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StaffEntity> findByFirstName(String firstName) {
        return staffDao.findByStaffName(firstName);
    }
}
