package com.example.backend_spring_boot_final_project.dao;

import com.example.backend_spring_boot_final_project.entity.impl.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FieldDao extends JpaRepository<FieldEntity,String> {
    @Query("SELECT f FROM FieldEntity f WHERE f.field_name = :field_name")
    Optional<FieldEntity> findByFieldName(@Param("field_name") String field_name);
}
