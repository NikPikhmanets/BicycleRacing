package com.bicycle.racing.events;

import com.bicycle.racing.events.data.mapper.EventRowMapper;
import com.bicycle.racing.events.data.model.Event;
import com.bicycle.racing.utils.ResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class EventRepositoryImpl implements EventRepository<Event> {

    private static final String FIND_ALL_EVENTS = "findAllEvents.sql";
    private static final String INSERT_LIST = "insertList.sql";

    private final NamedParameterJdbcTemplate parameterJdbcTemplate;

    @Autowired
    public EventRepositoryImpl(DataSource dataSource) {
        this.parameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Optional<List<Event>> findAll() {
        try {
            List<Event> events = parameterJdbcTemplate.query(getSQL(FIND_ALL_EVENTS), new EventRowMapper());
            return Optional.of(events);
        } catch (DataAccessException ex) {
            ex.printStackTrace();

            return Optional.empty();
        }
    }

    @Override
    public void insertList(List<Event> list) {
        parameterJdbcTemplate.batchUpdate(
                getSQL(INSERT_LIST),
                SqlParameterSourceUtils.createBatch(list.toArray())
        );
    }

    private String getSQL(String resource) {
        return ResourceUtils.resourceAsString(EventRepository.class, resource);
    }
}
