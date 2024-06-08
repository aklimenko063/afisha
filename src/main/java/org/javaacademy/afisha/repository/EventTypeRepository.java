package org.javaacademy.afisha.repository;

import lombok.RequiredArgsConstructor;
import org.javaacademy.afisha.model.entity.EventType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EventTypeRepository {
    private final JdbcTemplate jdbcTemplate;

    public void addNewEntity(EventType eventType) {
        String sql = "insert into application.event_type (name) values (?)";
        jdbcTemplate.update(sql, ps -> ps.setString(1, eventType.getName()));
    }

    public Optional<EventType> findEntityById(Integer id) {
        String sql = "select * from application.event_type where id = ?";
        EventType eventType = jdbcTemplate.queryForObject(sql, this::eventTypeRowMapper, id);
        return Optional.ofNullable(eventType);
    }

    public Optional<EventType> findEntityByName(String name) {
        String sql = "select * from application.event_type where name = ? limit 1";
        EventType eventType = jdbcTemplate.queryForObject(sql, this::eventTypeRowMapper, name);
        return Optional.ofNullable(eventType);
    }

    public List<EventType> findAllEntity() {
        String sql = "select * from application.event_type order by id";
        return jdbcTemplate.query(sql, this::eventTypeRowMapper);
    }

    private EventType eventTypeRowMapper(ResultSet rs, int rowNumber) {
        try {
            EventType eventTypeResult = new EventType();
            eventTypeResult.setId(rs.getInt("id"));
            eventTypeResult.setName(rs.getString("name"));
            return eventTypeResult;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
