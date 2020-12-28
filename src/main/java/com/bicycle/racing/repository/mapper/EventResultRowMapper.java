package com.bicycle.racing.repository.mapper;

import com.bicycle.racing.model.EventResult;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

@Component
public class EventResultRowMapper implements RowMapper<EventResult> {

    @Override
    public EventResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        EventResult eventResult = new EventResult();
        eventResult.setId(rowNum);
        eventResult.setEventId(rs.getInt("event_id"));
        eventResult.setUsername(rs.getString("username"));
        eventResult.setTime(rs.getObject("time", LocalTime.class));
        eventResult.setStatus(rs.getBoolean("status"));

        return eventResult;
    }
}
