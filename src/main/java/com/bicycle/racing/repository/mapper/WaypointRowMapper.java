package com.bicycle.racing.repository.mapper;

import com.bicycle.racing.file.gpx.data.Waypoint;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class WaypointRowMapper implements RowMapper<Waypoint> {

    @Override
    public Waypoint mapRow(ResultSet rs, int rowNum) throws SQLException {
        Waypoint waypoint = new Waypoint();
        waypoint.setLatitude(rs.getDouble("latitude"));
        waypoint.setLongitude(rs.getDouble("longitude"));
        waypoint.setTime(rs.getObject("date", LocalDateTime.class));

        return waypoint;
    }
}
