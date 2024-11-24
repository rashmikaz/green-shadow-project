package com.example.backend_spring_boot_final_project.service.impl;

import com.example.backend_spring_boot_final_project.dao.UserDao;
import com.example.backend_spring_boot_final_project.dto.impl.UserDTO;
import com.example.backend_spring_boot_final_project.entity.impl.UserEntity;
import com.example.backend_spring_boot_final_project.exception.DataPersistException;
import com.example.backend_spring_boot_final_project.service.UserService;
import com.example.backend_spring_boot_final_project.util.AppUtil;
import com.example.backend_spring_boot_final_project.util.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveUser(UserDTO userDTO){
        userDTO.setUser_id(AppUtil.generateUserId());
        UserEntity userEntity = userDao.save(mapping.toUserEntity(userDTO));
        if (userEntity==null){
            throw new DataPersistException("user not saved");
        }


    }

}
