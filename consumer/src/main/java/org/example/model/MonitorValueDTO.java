package org.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class MonitorValueDTO {
    private Long monId;

    private String value;

    private Instant timestamp;
}
