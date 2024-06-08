package org.javaacademy.afisha.model.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Ticket {
    private Integer id;
    private Integer eventId;
    private String clientEmail;
    private BigDecimal price;
    private boolean isSelled;
}
