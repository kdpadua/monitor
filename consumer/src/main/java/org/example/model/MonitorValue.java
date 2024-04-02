package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "MONITOR_VALUE",schema="public")
public class MonitorValue {

    public MonitorValue(MonitorValueDTO val, Monitor mon) {
        this.mon = mon;
        this.measurement = val.getValue();
        this.timestamp = val.getTimestamp();
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
