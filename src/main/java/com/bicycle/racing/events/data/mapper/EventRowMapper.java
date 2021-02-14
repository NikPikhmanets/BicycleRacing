package com.bicycle.racing.events.data.mapper;

import com.bicycle.racing.events.data.model.Event;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.bicycle.racing.events.data.EventConstants.*;

public class EventRowMapper implements RowMapper<Event> {

    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Event.builder()
                .id(rs.getInt(ID))
                .type(rs.getString(TYPE))
                .title(rs.getString(TITLE))
                .sampleTrackId(rs.getInt(SAMPLE_TRACK_ID))
                .timeStart(rs.getObject(TIME_START, LocalDateTime.class))
                .timeLimit(rs.getObject(TIME_LIMIT, LocalTime.class))
                .distance(rs.getInt(DISTANCE))
                .content(rs.getString(CONTENT))
                .createdAt(rs.getObject(CREATED_AT, LocalDateTime.class))
                .editedAt(rs.getObject(EDITED_AT, LocalDateTime.class))
                .build();
    }
}
