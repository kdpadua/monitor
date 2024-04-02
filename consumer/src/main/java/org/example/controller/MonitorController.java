package org.example.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.example.service.MonitorService;
import org.example.model.Monitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("monitor")
public class MonitorController {
    @Autowired
    private MonitorService monitorService;

    @GetMapping
    public List<Monitor> getAll() {
        return monitorService.findAll();
    }

    @GetMapping("/{monId}")
    public Monitor getMonitor(@PathVariable long monId){
        return monitorService.findById(monId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "monitor with ID " + monId + " not found.")
        );
    }

    @PostMapping
    @JsonIgnoreProperties("id")
    public boolean create(@RequestBody Monitor mon) {

        if(mon.getId() != null && monitorService.findById(mon.getId()).isEmpty())
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "monitor already exists. Use PUT.");

        return monitorService.saveOrUpdate(mon);
    }

    @PutMapping("/{monId}")
    @JsonIgnoreProperties("id")
    public boolean update(@PathVariable long monId, @RequestBody Monitor mon) {

        if(monitorService.findById(monId).isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "monitor does not exist. Use POST.");

        mon.setId(monId);
        return monitorService.saveOrUpdate(mon);
    }

    @DeleteMapping("/{monId}")
    public boolean delete(@PathVariable long monId) {

        if(monitorService.findById(monId).isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "monitor with ID " + monId + " not found.");

        return monitorService.delete(monId);
    }
}
