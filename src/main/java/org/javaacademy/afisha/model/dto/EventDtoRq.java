package org.javaacademy.afisha.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class EventDtoRq {
    private BigDecimal price;
    private LocalDateTime eventDate;
    private String name;
    private String eventTypeName;
    private String placeName;
}
