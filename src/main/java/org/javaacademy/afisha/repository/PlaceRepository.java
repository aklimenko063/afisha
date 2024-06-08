package org.javaacademy.afisha.repository;

import lombok.RequiredArgsConstructor;
import org.javaacademy.afisha.model.entity.Place;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlaceRepository {
    private final JdbcTemplate jdbcTemplate;

    public void addNewEntity(Place place) {
        String sql = """
                insert into application.place (name, address, city)
                values (?, ?, ?)
                """;
        jdbcTemplate.update(sql, ps -> psSetValues(ps, place));
    }

    public Optional<Place> findEntityById(Integer id) {
        String sql = "select * from application.place where id = ?";
        Place place = jdbcTemplate.queryForObject(sql, this::eventRowMapper, id);
        return Optional.ofNullable(place);
    }

    public Optional<Place> findPlaceByName(String placeName) {
        String sql = "select * from application.place where name = ? limit 1";
        Place place = jdbcTemplate.queryForObject(sql, this::eventRowMapper, placeName);
        return Optional.ofNullable(place);
    }

    public List<Place> findAllEntity() {
        String sql = "select * from application.place order by id";
        return jdbcTemplate.query(sql, this::eventRowMapper);
    }

    private Place eventRowMapper(ResultSet rs, int rowNumber) {
        try {
            Place placeResult = new Place();
            placeResult.setId(rs.getInt("id"));
            placeResult.setName(rs.getString("name"));
            placeResult.setAddress(rs.getString("address"));
            placeResult.setCity(rs.getString("city"));
            return placeResult;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void psSetValues(PreparedStatement ps, Place place) throws SQLException {
        ps.setString(1, place.getName());
        ps.setString(2, place.getAddress());
        ps.setString(3, place.getCity());
    }


}
