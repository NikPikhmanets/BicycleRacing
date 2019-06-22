package com.bicycleracing.repository.mapper;

import com.bicycleracing.model.UserEvent;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class UserEventRowMapper implements RowMapper<UserEvent> {

    @Override
    public UserEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserEvent userEvent = new UserEvent();
        userEvent.setEventId(rs.getInt("event_id"));
        userEvent.setNameEvent(rs.getString("title"));
        userEvent.setTypeEvent(rs.getString("type"));
        userEvent.setDistance(rs.getInt("distance"));
        userEvent.setTimeStart(rs.getObject("time_start", LocalDateTime.class));
        userEvent.setTrackName(rs.getString("name"));

        return userEvent;
    }
}
