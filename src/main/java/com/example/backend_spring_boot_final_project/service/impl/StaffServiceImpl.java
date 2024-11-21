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


}
