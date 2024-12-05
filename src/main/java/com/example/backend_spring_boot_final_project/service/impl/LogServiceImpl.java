package com.example.backend_spring_boot_final_project.service.impl;

import com.example.backend_spring_boot_final_project.dao.MonitoringLogDao;
import com.example.backend_spring_boot_final_project.dto.MonitoringLogStatus;
import com.example.backend_spring_boot_final_project.dto.impl.MonitoringLogDTO;
import com.example.backend_spring_boot_final_project.entity.impl.MonitoringLogEntity;
import com.example.backend_spring_boot_final_project.exception.DataPersistException;
import com.example.backend_spring_boot_final_project.service.LogService;
import com.example.backend_spring_boot_final_project.statuscode.SelectedErrorStatus;
import com.example.backend_spring_boot_final_project.util.AppUtil;
import com.example.backend_spring_boot_final_project.util.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LogServiceImpl implements LogService {

    @Autowired
    private MonitoringLogDao monitoringLogDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveLog(MonitoringLogDTO monitoringLogDTO) {
        monitoringLogDTO.setLog_code(AppUtil.generateLogId());
        MonitoringLogEntity saveLog = monitoringLogDao.save(mapping.toMonitoringLogEntity(monitoringLogDTO));
        if(saveLog == null){
            throw new DataPersistException("Log not saved");
        }
    }

    @Override
    public MonitoringLogStatus getLog(String logCode) {
        if(monitoringLogDao.existsById(logCode)){
            var selectedLog = monitoringLogDao.getReferenceById(logCode);
            return mapping.toMonitoringLogDTO(selectedLog);
        }else{
            return new SelectedErrorStatus(2,"Selected Log not found");
        }
    }


    @Override
    public List<MonitoringLogDTO> getAllLogs() {
        List<MonitoringLogEntity> logs = monitoringLogDao.findAll();
        return logs.stream()
                .map(log -> {
                    MonitoringLogDTO monitoringLogDTO = new MonitoringLogDTO();
                    monitoringLogDTO.setLog_date(log.getLog_date());
                    monitoringLogDTO.setLog_details(log.getLog_details());
                    monitoringLogDTO.setObserved_image(log.getObserved_image());
                    return monitoringLogDTO;
                })
                .collect(Collectors.toList());
    }

}
