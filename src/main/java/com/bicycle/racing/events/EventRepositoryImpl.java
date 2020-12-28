package com.bicycle.racing.events;

import com.bicycle.racing.events.mapper.EventRowMapper;
import com.bicycle.racing.events.model.Event;
import com.bicycle.racing.utils.ResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Repository
public class EventRepositoryImpl implements EventRepository {

    private static final String FIND_ALL_EVENTS = "findAllEvents.sql";

    private final NamedParameterJdbcTemplate parameterJdbcTemplate;

    @Autowired
    public EventRepositoryImpl(DataSource dataSource) {
        this.parameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Event> findAll() {
        String sql = ResourceUtils.resourceAsString(EventRepository.class, FIND_ALL_EVENTS);

        try {
            return parameterJdbcTemplate.query(sql, new EventRowMapper());
        } catch (DataAccessException ex) {
            return Collections.emptyList();
        }
    }
}
