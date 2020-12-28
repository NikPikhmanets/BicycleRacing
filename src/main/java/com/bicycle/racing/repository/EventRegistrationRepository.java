package com.bicycle.racing.repository;

import com.bicycle.racing.repository.mapper.EventRegistrationRowMapper;
import com.bicycle.racing.model.EventRegistration;
import com.bicycle.racing.model.UserEvent;
import com.bicycle.racing.model.form.UserForm;
import com.bicycle.racing.repository.mapper.UserEventRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class EventRegistrationRepository {

    private final NamedParameterJdbcTemplate parameterJdbcTemplate;
    private final EventRegistrationRowMapper rowMapper;
    private final UserEventRowMapper userEventRowMapper;

    @Autowired
    public EventRegistrationRepository(DataSource dataSource,
                                       EventRegistrationRowMapper rowMapper,
                                       UserEventRowMapper userEventRowMapper) {
        this.parameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.rowMapper = rowMapper;
        this.userEventRowMapper = userEventRowMapper;
    }

    public void addUserToEvent(EventRegistration eventRegistration) {
        String sql = "INSERT INTO event_registr (event_id, username) values(:event_id, :username)";
        parameterJdbcTemplate.update(sql,
                new MapSqlParameterSource()
                        .addValue("event_id", eventRegistration.getEventId())
                        .addValue("username", eventRegistration.getUsername()));
    }

    public int getTrackIdByEventId(int eventId, String username) {
        String sql = "SELECT track_id FROM event_registr WHERE event_id = :event_id AND username = :username";
        Integer trackId = parameterJdbcTemplate.queryForObject(
                sql,
                new MapSqlParameterSource()
                        .addValue("event_id", eventId)
                        .addValue("username", username),
                Integer.class
        );
        return (trackId != null ? trackId : -1);
    }

    public void saveTrackId(UserForm userForm) {
        String sql =
                "UPDATE event_registr SET track_id = :track_id WHERE event_id = :event_id AND username = :username";
        parameterJdbcTemplate.update(
                sql,
                new MapSqlParameterSource()
                        .addValue("track_id", userForm.getTrackId())
                        .addValue("event_id", userForm.getEventId())
                        .addValue("username", userForm.getUsername())
        );
    }

    public EventRegistration getEventByUser(EventRegistration eventRegistration) {
        String sql = "SELECT * FROM event_registr WHERE event_id = :event_id AND username = :username";
        List<EventRegistration> eventRegistrations = parameterJdbcTemplate.query(sql,
                new MapSqlParameterSource()
                        .addValue("event_id", eventRegistration.getEventId())
                        .addValue("username", eventRegistration.getUsername()),
                rowMapper
        );
        return (eventRegistrations.isEmpty()) ? null : eventRegistrations.get(0);
    }

    public int getTotalCount(String username) {
        String sql = "SELECT COUNT(*) FROM event_registr WHERE username = :username";
        Integer result = parameterJdbcTemplate.queryForObject(
                sql,
                new MapSqlParameterSource("username", username),
                Integer.class
        );
        return (result != null ? result : 0);
    }

    public List<UserEvent> getUserEventsPage(String username, int startItem, int pageSize) {
        String sql = "SELECT event_id, event.title,event.type, event.distance, event.time_start, track.name " +
                "FROM event " +
                "LEFT JOIN event_registr ON event_registr.event_id = event.id " +
                "LEFT JOIN track ON event_registr.track_id = track.id " +
                "WHERE username = :username " +
                "OFFSET " + startItem + " LIMIT " + pageSize;
        return parameterJdbcTemplate.query(
                sql,
                new MapSqlParameterSource("username", username),
                userEventRowMapper
        );
    }
}
