package com.example.backend_spring_boot_final_project.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Entity
//@Table(name = "monitoring_log")
//public class MonitoringLogEntity {
//    @Id
//    private String log_code;
//    private String log_date;
//    private String log_details;
//    @Column(columnDefinition = "LONGTEXT")
//    private String observed_image;
//    @OneToMany(mappedBy = "field_code",cascade = CascadeType.ALL)
//    private List<FieldEntity> fields;
//    @OneToMany(mappedBy = "crop_code",cascade = CascadeType.ALL)
//    private List<CropEntity> crops;
//    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL)
//    private List<StaffEntity> staff;
//}
