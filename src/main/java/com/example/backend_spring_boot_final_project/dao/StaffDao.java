package com.example.backend_spring_boot_final_project.dao;

import com.example.backend_spring_boot_final_project.entity.impl.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffDao extends JpaRepository<StaffEntity,String> {
}
