package com.example.backend_spring_boot_final_project.entity.impl;

import com.example.backend_spring_boot_final_project.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Field")
public class FieldEntity implements SuperEntity {
    @Id
    private String field_code;
    private String field_name;
    private Point location;
    private Double extent_size;
    @Column(columnDefinition = "LONGTEXT")
    private String field_image1;
    @Column(columnDefinition = "LONGTEXT")
    private String field_image2;
//    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
//    private List<CropEntity> crops;
//    @ManyToMany(mappedBy = "fields")
//    private List<StaffEntity> allocated_staff;
}
