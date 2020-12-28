package com.bicycle.racing.repository;

import com.bicycle.racing.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class EventRepository {

    private final JdbcTemplate jdbcTemplatel;
    private final NamedParameterJdbcTemplate parameterJdbcTemplate;

    @Autowired
    public EventRepository(DataSource dataSource) {
        this.jdbcTemplatel = new JdbcTemplate(dataSource);
        this.parameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void save(Event event) {
        String sql = "INSERT INTO event (type, title, time_start, time_limit, content, distance, track_sample_id) " +
                "values(:type, :title, :time_start, :time_limit, :content, :distance, :track_sample_id)";
        parameterJdbcTemplate.update(
                sql,
                new MapSqlParameterSource()
                        .addValue("type", event.getType())
                        .addValue("title", event.getTitle())
                        .addValue("time_start", event.getTimeStart())
                        .addValue("time_limit", event.getTimeLimit())
                        .addValue("content", event.getContent())
                        .addValue("distance", event.getDistance())
                        .addValue("track_sample_id", event.getSampleTrackId())
        );
    }

    public List<Event> getEvents(int startItem, int size) {
        String sql = "SELECT * FROM event ORDER BY time_start OFFSET " + startItem + " LIMIT " + size;

        return parameterJdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Event.class)
        );
    }

    public int getTotalCount() {
        String sql = "SELECT COUNT(*) AS total FROM event";
        Integer result = jdbcTemplatel.queryForObject(sql, Integer.class);

        return (result != null ? result : 0);
    }

    public int getSampleTrackId(int eventId) {
        String sql = "SELECT track_sample_id FROM event WHERE event.id = :eventId";
        Integer result = parameterJdbcTemplate.queryForObject(
                sql,
                new MapSqlParameterSource("eventId", eventId),
                Integer.class
        );
        return (result != null ? result : 0);
    }

    public Event getEventById(int id) {
        String sql = "SELECT * FROM event WHERE id = :id";

        return parameterJdbcTemplate.queryForObject(
                sql,
                new MapSqlParameterSource("id", id),
                new BeanPropertyRowMapper<>(Event.class)
        );
    }
}
