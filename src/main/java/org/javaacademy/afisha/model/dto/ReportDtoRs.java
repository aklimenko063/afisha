package org.javaacademy.afisha.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReportDtoRs {
    private String eventName;
    private String eventTypeName;
    private Integer soldTicketsCount;
    private BigDecimal soldTicketsTotalCost;
}
