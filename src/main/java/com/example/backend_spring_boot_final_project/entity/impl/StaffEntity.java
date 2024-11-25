package com.example.backend_spring_boot_final_project.entity.impl;


import com.example.backend_spring_boot_final_project.entity.Gender;
import com.example.backend_spring_boot_final_project.entity.Role;
import com.example.backend_spring_boot_final_project.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "staff")
public class StaffEntity implements SuperEntity {
    @Id
    private String staffId;
    private String firstName;
    private String lastName;
    private String designation;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String joinedDate;
    private String dob;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String contactNo;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToMany
    @JoinTable(name = "Field_Staff_assignment",joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "field_code"))
    private List<FieldEntity> fields;
    @OneToMany(mappedBy = "assigned_staff",cascade = CascadeType.ALL)
    private List<VehicleEntity> vehicles;
}
