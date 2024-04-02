package org.example.kafka;

import org.example.model.MonitorValueDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, MonitorValueDTO> kafkaTemplate;

    public void sendMessage(MonitorValueDTO value) {
        kafkaTemplate.send("MONITOR-DATA", value);
        LOGGER.info(String.format("Monitor data sent. Timestamp: %s, Value: %s", value.getTimestamp(), value.getValue()));
    }
}
