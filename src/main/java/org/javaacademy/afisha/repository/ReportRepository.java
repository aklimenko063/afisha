package org.javaacademy.afisha.repository;

import lombok.RequiredArgsConstructor;
import org.javaacademy.afisha.model.view.Report;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReportRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Report> getData() {
        String sql = "select * from application.report_view";
        return jdbcTemplate.query(sql, this::reportRowMapper);
    }

    private Report reportRowMapper(ResultSet rs, int rowNumber) {
        try {
            Report report = new Report();
            report.setEventName(rs.getString("event_name"));
            report.setEventTypeName(rs.getString("event_type_name"));
            report.setSoldTicketsCount(rs.getInt("sold_tickets_count"));
            report.setSoldTicketsTotalCost(rs.getBigDecimal("sold_tickets_total_cost"));
            return report;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
