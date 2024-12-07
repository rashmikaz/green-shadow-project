package com.example.backend_spring_boot_final_project.dao;

import com.example.backend_spring_boot_final_project.entity.impl.FieldEntity;
import com.example.backend_spring_boot_final_project.entity.impl.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffDao extends JpaRepository<StaffEntity,String> {

    @Query("SELECT s FROM StaffEntity s WHERE s.first_name = :first_name")
    Optional<StaffEntity> findByStaffName(@Param("first_name") String assignedStaff);

    @Query("SELECT s FROM StaffEntity s WHERE s.first_name IN :staffs")
    List<StaffEntity> findByStaffNameList(@Param("staffs") List<String> staffs);
}
