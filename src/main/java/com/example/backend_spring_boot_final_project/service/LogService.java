package com.example.backend_spring_boot_final_project.service;

import com.example.backend_spring_boot_final_project.dto.MonitoringLogStatus;
import com.example.backend_spring_boot_final_project.dto.impl.MonitoringLogDTO;

public interface LogService {

    void saveLog(MonitoringLogDTO monitoringLogDTO);

    MonitoringLogStatus getLog(String logCode);
}
