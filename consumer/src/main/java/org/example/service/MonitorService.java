package org.example.service;

import org.example.dao.MonitorRepository;
import org.example.model.Monitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MonitorService {

    @Autowired
    private MonitorRepository monitorRepository;
    static Map<Long, Monitor> repo = new HashMap<>();

    static {
        Monitor temp = new Monitor("temperature", "Temperature of the system");
        temp.setId(1L);
        repo.put(temp.getId(), temp);

        Monitor cpu = new Monitor("CPU", "CPU load");
        cpu.setId(2L);
        repo.put(cpu.getId(), cpu);
    }

    public boolean saveOrUpdate(Monitor mon) {
        monitorRepository.save(mon);

        return true;
    }

    public List<Monitor> findAll() {
        return monitorRepository.findAll();
    }

    public Optional<Monitor> findById(Long id) {
        return monitorRepository.findById(id);
    }

    public boolean delete(Long id) {

        if(findById(id).isPresent()) {
            monitorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
