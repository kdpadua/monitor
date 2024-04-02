package org.example.dao;

import org.example.model.MonitorValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonitorValueRepository extends JpaRepository<MonitorValue, Long> {
    List<MonitorValue> findAllByMonId(Long monId);
}
