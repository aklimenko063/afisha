package org.javaacademy.afisha.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TicketDtoRs {
    private Integer id;
    private String eventName;
    private String eventTypeName;
    private String placeName;
    private String placeAddress;
    private String placeCity;
    private LocalDateTime eventDate;
    private String email;
    private BigDecimal price;
}
