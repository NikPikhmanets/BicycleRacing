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
import java.util.Collections;
import java.util.List;

@Repository
public class EventRepositoryImpl implements EventRepository {

    private static final String FIND_ALL_EVENTS = "findAllEvents.sql";
    private static final String INSERT_LIST = "insertList.sql";

    private final NamedParameterJdbcTemplate parameterJdbcTemplate;

    @Autowired
    public EventRepositoryImpl(DataSource dataSource) {
        this.parameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Event> findAll() {
        try {
            return parameterJdbcTemplate.query(getSQL(FIND_ALL_EVENTS), new EventRowMapper());
        } catch (DataAccessException ex) {
            ex.printStackTrace();

            return Collections.emptyList();
        }
    }

    @Override
    public void saveAll(List<Event> list) {
        parameterJdbcTemplate.batchUpdate(
                getSQL(INSERT_LIST),
                SqlParameterSourceUtils.createBatch(list.toArray())
        );
    }

    private String getSQL(String resource) {
        return ResourceUtils.resourceAsString(EventRepository.class, resource);
    }
}
