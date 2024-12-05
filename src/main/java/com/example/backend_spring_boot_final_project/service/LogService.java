package com.example.backend_spring_boot_final_project.service;

import com.example.backend_spring_boot_final_project.dto.MonitoringLogStatus;
import com.example.backend_spring_boot_final_project.dto.impl.MonitoringLogDTO;
import com.example.backend_spring_boot_final_project.entity.impl.MonitoringLogEntity;

import java.util.List;
import java.util.Optional;

public interface LogService {

    void saveLog(MonitoringLogDTO monitoringLogDTO);

    MonitoringLogStatus getLog(String logCode);


    List<MonitoringLogDTO> getAllLogs();

    void deleteLog(String logCode);

    void updateLog(String logCode, MonitoringLogDTO monitoringLogDTO);

    Optional<MonitoringLogEntity> findByLogDesc(String logDesc);
}
