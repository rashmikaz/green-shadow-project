package com.example.backend_spring_boot_final_project.entity.impl;

import ch.qos.logback.core.status.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "vehicle")
public class VehicleEntity {
    @Id
    private String vehicle_code;
    private String licensePlateNumber;
    private String vehicleCategory;
    private String fuelType;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String remarks;
    @ManyToOne
    @JoinColumn(name = "id")
    private StaffEntity assigned_staff;
}
