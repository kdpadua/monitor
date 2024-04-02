package org.example.kafka;

import org.example.model.Monitor;
import org.example.model.MonitorValue;
import org.example.model.MonitorValueDTO;
import org.example.service.MonitorService;
import org.example.service.MonitorValueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private MonitorValueService monitorValueService;
    @Autowired
    private MonitorService monitorService;

    @KafkaListener(topics = "MONITOR-DATA", groupId = "consumers")
    public void consume(MonitorValueDTO dto) {

        monitorValueService.save(dtoToEntity(dto));
        LOGGER.info(String.format("Monitor data received. Timestamp: %s, Value: %s", dto.getTimestamp(), dto.getValue()));
    }

    private MonitorValue dtoToEntity(MonitorValueDTO dto) {
        Monitor mon = monitorService.findById(dto.getMonId()).orElseThrow();
        return new MonitorValue(mon, dto.getValue(), dto.getTimestamp());
    }
}
