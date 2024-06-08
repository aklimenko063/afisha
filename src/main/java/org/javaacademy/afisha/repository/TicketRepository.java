package org.javaacademy.afisha.repository;

import lombok.RequiredArgsConstructor;
import org.javaacademy.afisha.model.entity.Event;
import org.javaacademy.afisha.model.entity.Ticket;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TicketRepository {
    private final JdbcTemplate jdbcTemplate;

    public void addNewEntityFromEvents(Ticket ticket) {
        String sql = """
                insert into application.ticket (event_id, price)
                values (?, ?)
                """;
        jdbcTemplate.update(sql, ps -> psSetValuesFromEvents(ps, ticket));
    }

    public void addNewEntity(Ticket ticket) {
        String sql = """
                insert into application.ticket (event_id, price, is_selled, client_email)
                values (?, ?, ?, ?)
                """;
        jdbcTemplate.update(sql, ps -> psSetValues(ps, ticket));
    }

    public Optional<Ticket> findEntityById(Integer id) {
        String sql = "select * from application.ticket where id = ?";
        Ticket ticket = jdbcTemplate.queryForObject(sql, this::ticketRowMapper, id);
        return Optional.ofNullable(ticket);
    }

    public Optional<Ticket> findLastCreatedTicket(Ticket ticket, Event event) {
        String sql = """
                select *
                from application.ticket
                where event_id = ? and client_email = ? and is_selled = true
                order by id desc
                limit 1
                """;
        Ticket findingTicket = jdbcTemplate.queryForObject(
                sql,
                this::ticketRowMapper,
                event.getId(), ticket.getClientEmail());
        return Optional.ofNullable(findingTicket);
    }

    public Optional<Ticket> findTicketByEvent(Event event) {
        String sql = """
                select *
                from application.ticket
                where event_id = ? and is_selled = false
                limit 1
                """;
        Ticket findingTicket = jdbcTemplate.queryForObject(
                sql,
                this::ticketRowMapper,
                event.getId());
        return Optional.ofNullable(findingTicket);
    }

    public List<Ticket> findAllEntity() {
        String sql = "select * from application.ticket order by id asc";
        return jdbcTemplate.query(sql, this::ticketRowMapper);
    }

    public void updateTicket(Ticket ticket, String email) {
        String sql = """
                update application.ticket
                set client_email = ?,
                is_selled = true
                where id = ?
                """;
        jdbcTemplate.update(sql, email, ticket.getId());
    }

    private Ticket ticketRowMapper(ResultSet rs, int rowNumber) {
        try {
            Ticket ticketResult = new Ticket();
            ticketResult.setId(rs.getInt("id"));
            ticketResult.setEventId(rs.getInt("event_id"));
            ticketResult.setClientEmail(rs.getString("client_email"));
            ticketResult.setPrice(rs.getBigDecimal("price"));
            ticketResult.setSelled(rs.getBoolean("is_selled"));
            return ticketResult;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void psSetValuesFromEvents(PreparedStatement ps, Ticket ticket) throws SQLException {
        ps.setInt(1, ticket.getEventId());
        ps.setBigDecimal(2, ticket.getPrice());
    }

    private void psSetValues(PreparedStatement ps, Ticket ticket) throws SQLException {
        ps.setInt(1, ticket.getEventId());
        ps.setBigDecimal(2, ticket.getPrice());
        ps.setBoolean(3, ticket.isSelled());
        ps.setString(4, ticket.getClientEmail());
    }
}
