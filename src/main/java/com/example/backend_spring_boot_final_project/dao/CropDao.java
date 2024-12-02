package com.example.backend_spring_boot_final_project.dao;

import com.example.backend_spring_boot_final_project.entity.impl.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CropDao extends JpaRepository<CropEntity,String> {

    @Query("SELECT c FROM CropEntity c WHERE c.common_name = :common_name")
    Optional<CropEntity> findByCropName(@Param("common_name") String common_name);

}
