package org.example.service;

import org.example.model.Monitor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class MonitorServiceTest {

    @Autowired
    private MonitorService monitorService;

    @Test
    void testSave() {
        Monitor mon = new Monitor();
        mon.setId(1L);
        mon.setName("CPU");
        mon.setDescription("System CPU");

        monitorService.saveOrUpdate(mon);
        Optional<Monitor> testMon = monitorService.findById(1L);

        Assertions.assertTrue(testMon.isPresent());
        Assertions.assertEquals(1L, testMon.get().getId());
        Assertions.assertEquals("CPU", testMon.get().getName());
        Assertions.assertEquals("System CPU", testMon.get().getDescription());

        mon.setDescription("another description");

        monitorService.saveOrUpdate(mon);
        testMon = monitorService.findById(1L);


        Assertions.assertTrue(testMon.isPresent());
        Assertions.assertEquals(1L, testMon.get().getId());
        Assertions.assertEquals("CPU", testMon.get().getName());
        Assertions.assertEquals("another description", testMon.get().getDescription());
    }
}
