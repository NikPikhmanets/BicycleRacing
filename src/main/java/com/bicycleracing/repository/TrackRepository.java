package com.bicycleracing.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Objects;

@Repository
public class TrackRepository {

    private final NamedParameterJdbcTemplate parameterJdbcTemplate;

    @Autowired
    public TrackRepository(DataSource dataSource) {
        this.parameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public int saveNameTrack(String trackName) {
        String sql = "INSERT INTO track(name) VALUES(:name)";

        return save(trackName, sql);
    }

    public int saveNameSampleTrack(String trackName) {
        String sql = "INSERT INTO track_sample(name) VALUES(:name)";

        return save(trackName, sql);
    }

    private int save(String trackName, String sql) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name", trackName);
        parameterJdbcTemplate.update(sql, namedParameters, holder, new String[]{"id"});

        return Objects.requireNonNull(holder.getKey()).intValue();
    }

    public void updateNameTrack(int trackId, String name) {
        String sql =
                "UPDATE track SET name = :name WHERE id = :trackId";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name", name)
                .addValue("trackId", trackId);
        parameterJdbcTemplate.update(sql, namedParameters);
    }
}
