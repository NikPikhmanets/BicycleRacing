package com.bicycle.racing.events.mapper;

import com.bicycle.racing.events.model.Event;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EventRowMapper implements RowMapper<Event> {

    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Event.builder()
                .id(rs.getInt("id"))
                .type(rs.getString("type"))
                .title(rs.getString("title"))
                .sampleTrackId(rs.getInt("track_sample_id"))
                .timeStart(rs.getObject("time_start", LocalDateTime.class))
                .timeLimit(rs.getObject("time_limit", LocalTime.class))
                .distance(rs.getInt("distance"))
                .content(rs.getString("content"))
                .build();
    }
}
