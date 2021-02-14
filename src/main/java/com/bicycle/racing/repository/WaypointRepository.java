package com.bicycle.racing.repository;

import com.bicycle.racing.file.gpx.data.Waypoint;
import com.bicycle.racing.model.BatchWaypoint;
import com.bicycle.racing.repository.mapper.WaypointRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class WaypointRepository {

    private final NamedParameterJdbcTemplate parameterJdbcTemplate;
    private final WaypointRowMapper waypointRowMapper;

    @Autowired
    public WaypointRepository(DataSource dataSource,
                              WaypointRowMapper waypointRowMapper) {
        this.parameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.waypointRowMapper = waypointRowMapper;
    }

    public List<Waypoint> getSimpleWaypointsByTrackId(int sampleTrackId) {
        String sql = "SELECT * FROM waypoint WHERE waypoint.track_id = :sampleTrackId";

        return parameterJdbcTemplate.query(
                sql,
                new MapSqlParameterSource()
                        .addValue("sampleTrackId", sampleTrackId),
                waypointRowMapper
        );
    }

    public void saveSampleWaypoints(List<BatchWaypoint> waypoints) {
        String sql = "INSERT INTO sample_waypoint (latitude, longitude, date, track_id) " +
                "VALUES(:latitude, :longitude, :date, :track_id)";
        parameterJdbcTemplate.batchUpdate(sql, SqlParameterSourceUtils.createBatch(waypoints.toArray()));
    }

    public void saveWayPoints(List<BatchWaypoint> waypoints) {
        String sql = "INSERT INTO waypoint (latitude, longitude, date, track_id) " +
                "VALUES(:latitude, :longitude, :date, :track_id)";
        parameterJdbcTemplate.batchUpdate(sql, SqlParameterSourceUtils.createBatch(waypoints.toArray()));
    }

    public void deleteWaypoints(int trackId) {
        String sql = "DELETE FROM waypoint WHERE track_id = :trackId";
        parameterJdbcTemplate.update(
                sql,
                new MapSqlParameterSource("trackId", trackId)
        );
    }
}
