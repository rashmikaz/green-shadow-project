package com.example.backend_spring_boot_final_project.dto.impl;



import com.example.backend_spring_boot_final_project.entity.Gender;
import com.example.backend_spring_boot_final_project.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO {
    private String staffId;
    private String firstName;
    private String lastName;
    private String designation;
    private Gender gender;
    private String joinedDate;
    private String dob;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String contactNo;
    private String email;
    private Role role;
//    private List<FieldDTO> fields;
//    private List<VehicleDTO> vehicles;
}
