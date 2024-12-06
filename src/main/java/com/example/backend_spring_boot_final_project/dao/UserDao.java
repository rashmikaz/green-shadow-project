package com.example.backend_spring_boot_final_project.dao;

import com.example.backend_spring_boot_final_project.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserEntity,String> {
    Optional<UserEntity> findByEmail(String email);
}
