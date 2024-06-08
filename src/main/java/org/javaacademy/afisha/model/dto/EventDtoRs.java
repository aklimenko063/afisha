package org.javaacademy.afisha.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDtoRs {
    private String name;
    private String eventTypeName;
    private LocalDateTime eventDate;
    private String placeName;
}
