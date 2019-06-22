package com.bicycleracing.repository;

import com.bicycleracing.model.EventResult;
import com.bicycleracing.repository.mapper.EventResultRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class EventResultRepository {

    private final EventResultRowMapper eventResultRowMapper;
    private final NamedParameterJdbcTemplate parameterJdbcTemplate;

    @Autowired
    public EventResultRepository(EventResultRowMapper eventResultRowMapper,
                                 DataSource dataSource) {
        this.eventResultRowMapper = eventResultRowMapper;
        this.parameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void saveResult(EventResult eventResult) {
        String sql = "INSERT INTO event_result (event_id, username, time, status) values(:event_id, :username, :time, :status)";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("event_id", eventResult.getEventId())
                .addValue("username", eventResult.getUsername())
                .addValue("time", eventResult.getTime())
                .addValue("status", eventResult.isStatus());
        parameterJdbcTemplate.update(sql, namedParameters);
    }

    public void updateResult(EventResult eventResult) {
        String sql = "UPDATE event_result SET time = :timeRide, status = :status " +
                "WHERE event_id = :event_id AND username = :username";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("timeRide", eventResult.getTime())
                .addValue("status", eventResult.isStatus())
                .addValue("event_id", eventResult.getEventId())
                .addValue("username", eventResult.getUsername());
        parameterJdbcTemplate.update(sql, namedParameters);
    }

    public EventResult getResultByUsernameAndEventId(EventResult eventResult) {
        String sql = "SELECT * FROM event_result WHERE event_id = :event_id AND username = :username";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("event_id", eventResult.getEventId())
                .addValue("username", eventResult.getUsername());
        List<EventResult> results = parameterJdbcTemplate.query(sql, parameterSource, eventResultRowMapper);

        return results.isEmpty() ? null : results.get(0);
    }

    public List<EventResult> getResultByEventId(int eventId) {
        String sql = "SELECT * FROM event_result WHERE event_id = :event_id ORDER BY time, status";

        return parameterJdbcTemplate.query(sql,
                new MapSqlParameterSource()
                        .addValue("event_id", eventId),
                eventResultRowMapper
        );
    }
}
