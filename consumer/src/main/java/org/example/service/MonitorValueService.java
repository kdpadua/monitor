package org.example.service;

import org.example.dao.MonitorValueRepository;
import org.example.model.MonitorValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class MonitorValueService {
    @Autowired
    private MonitorValueRepository monitorValueRepository;

    public List<MonitorValue> findAllByMonid(Long monId) {
        return monitorValueRepository.findAllByMonId(monId);
    }

    public void save(MonitorValue value) {
        monitorValueRepository.save(value);
    }
}
