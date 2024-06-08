package org.javaacademy.afisha.repository;

import lombok.RequiredArgsConstructor;
import org.javaacademy.afisha.model.entity.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EventRepository {
    private final JdbcTemplate jdbcTemplate;

    public void addNewEntity(Event event) {
        String sql = """
                insert into application.event (name, event_type_id, event_date, place_id)
                values (?, ?, ?, ?)
                """;
        jdbcTemplate.update(sql, ps -> psSetValues(ps, event));
    }

    public Optional<Event> findEntityById(Integer id) {
        String sql = "select * from application.event where id = ?";
        Event event = jdbcTemplate.queryForObject(sql, this::eventRowMapper, id);
        return Optional.ofNullable(event);
    }

    public Optional<Event> findEntityByName(String name) {
        String sql = "select * from application.event where name = ? order by id desc limit 1";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, this::eventRowMapper, name));
    }

    public List<Event> findAllEntity() {
        String sql = "select * from application.event order by id";
        return jdbcTemplate.query(sql, this::eventRowMapper);
    }

    private Event eventRowMapper(ResultSet rs, int rowNumber) {
        try {
            Event eventResult = new Event();
            eventResult.setId(rs.getInt("id"));
            eventResult.setName(rs.getString("name"));
            eventResult.setEventTypeId(rs.getInt("event_type_id"));
            eventResult.setEventDate(rs.getTimestamp("event_date").toLocalDateTime());
            eventResult.setPlaceId(rs.getInt("place_id"));
            return eventResult;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void psSetValues(PreparedStatement ps, Event event) throws SQLException {
        ps.setString(1, event.getName());
        ps.setInt(2, event.getEventTypeId());
        ps.setTimestamp(3, Timestamp.valueOf(event.getEventDate()));
        ps.setInt(4, event.getPlaceId());
    }
}
