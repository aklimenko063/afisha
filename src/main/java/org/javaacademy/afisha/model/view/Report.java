package org.javaacademy.afisha.model.view;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Report {
    private String eventName;
    private String eventTypeName;
    private Integer soldTicketsCount;
    private BigDecimal soldTicketsTotalCost;
}
