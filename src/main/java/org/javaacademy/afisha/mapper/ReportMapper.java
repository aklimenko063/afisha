package org.javaacademy.afisha.mapper;

import org.javaacademy.afisha.model.dto.ReportDtoRs;
import org.javaacademy.afisha.model.view.Report;

public class ReportMapper {

    public static ReportDtoRs covertReportEntityToReportDtoRs(Report report) {
        ReportDtoRs reportDtoRs = new ReportDtoRs();
        reportDtoRs.setEventName(report.getEventName());
        reportDtoRs.setEventTypeName(report.getEventTypeName());
        reportDtoRs.setSoldTicketsCount(report.getSoldTicketsCount());
        reportDtoRs.setSoldTicketsTotalCost(report.getSoldTicketsTotalCost());
        return reportDtoRs;
    }
}
