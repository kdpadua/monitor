package org.example.dao;

import org.example.model.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitorRepository  extends JpaRepository<Monitor, Long> {
    Monitor findByName(String name);
}
