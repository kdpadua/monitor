package org.example.controller;

import org.example.dao.MonitorValueRepository;
import org.example.model.MonitorValue;
import org.example.service.MonitorValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("value")
public class MonitorValueController {

    @Autowired
    private MonitorValueService monitorValueService;

    @GetMapping("/{monId}")
    public List<MonitorValue> getMonitorValues(@PathVariable long monId){

        List<MonitorValue> values = monitorValueService.findAllByMonid(monId);

        if(values == null || values.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No values for monitor with ID " + monId + " found.");

        return values;
    }
}
