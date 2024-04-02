package org.example.tasks;

import org.example.kafka.KafkaProducer;
import org.example.model.MonitorValueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Random;

@Component
public class ReadMonitors {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Scheduled(fixedRate = 10000)
    public void pushMonitorValues() {
        MonitorValueDTO value = new MonitorValueDTO();
        value.setValue(String.valueOf(new Random().nextInt(1, 100)));
        value.setTimestamp(Instant.now());
        value.setMonId(1L);

        kafkaProducer.sendMessage(value);
    }
}
