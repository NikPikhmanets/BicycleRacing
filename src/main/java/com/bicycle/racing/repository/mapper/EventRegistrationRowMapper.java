package com.bicycle.racing.repository.mapper;

import com.bicycle.racing.model.EventRegistration;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EventRegistrationRowMapper implements RowMapper<EventRegistration> {

    @Override
    public EventRegistration mapRow(ResultSet rs, int rowNum) throws SQLException {
        EventRegistration event = new EventRegistration();
        event.setId(rs.getInt("id"));
        event.setEventId(rs.getInt("event_id"));
        event.setUsername(rs.getString("username"));
        event.setTrackId(rs.getInt("track_id"));

        return event;
    }
}
