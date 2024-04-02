package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "MONITOR_VALUE",schema="public")
public class MonitorValue {

    public MonitorValue(Monitor mon, String measurement, Instant timestamp) {
        this.mon = mon;
        this.measurement = measurement;
        this.timestamp = timestamp;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "monId")
    private Monitor mon;

    private String measurement;

    private Instant timestamp;
}
