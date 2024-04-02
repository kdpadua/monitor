package org.example.controller;

import org.example.model.Monitor;
import org.example.service.MonitorService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MonitorController.class)
public class MonitorControllerTest {

    @MockBean
    private MonitorService monitorService;

    @InjectMocks
    private MonitorController monitorController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetMonitor() throws Exception{
        Monitor mon = new Monitor();
        mon.setId(1L);
        mon.setName("CPU");
        mon.setDescription("System CPU");
        when(monitorService.findById(1L)).thenReturn(Optional.of(mon));

        this.mockMvc.perform(get("/monitor/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("CPU")))
                .andExpect(jsonPath("$.description", Matchers.is("System CPU")));

        this.mockMvc.perform(get("/monitor/123"))
                .andExpect(status().isNotFound())
                .andExpect(status().reason("monitor with ID 123 not found."));

        this.mockMvc.perform(get("/monitor/ABC"))
                .andExpect(status().isBadRequest());
    }
}
