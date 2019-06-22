package com.bicycleracing.repository;

import com.bicycleracing.model.User;
import com.bicycleracing.repository.mapper.UserRowMapper;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class UserRepository {

    private final NamedParameterJdbcTemplate parameterJdbcTemplate;
    private final UserRowMapper userRowMapper;

    @Autowired
    public UserRepository(HikariDataSource dataSource, UserRowMapper userRowMapper) {
        this.parameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.userRowMapper = userRowMapper;
    }

    public int saveUser(User user) {
        String sql = "INSERT INTO users (username, first_name, last_name, password, enabled) " +
                "VALUES(:username, :firstName, :lastName, :password, :enabled)";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("username", user.getUsername())
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("password", user.getPassword())
                .addValue("enabled", user.isEnabled());
        parameterJdbcTemplate.update(sql, namedParameters, holder, new String[]{"id"});

        return Objects.requireNonNull(holder.getKey()).intValue();
    }

    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE users.username = :username";
        List<User> users = parameterJdbcTemplate.query(
                sql,
                new MapSqlParameterSource("username", username),
                userRowMapper
        );
        return users.isEmpty() ? null : users.get(0);
    }

    public int getUserIdByUserName(String username) {
        String sql = "SELECT id FROM users WHERE users.username = :username";
        Integer result = parameterJdbcTemplate.queryForObject(
                sql,
                new MapSqlParameterSource("username", username),
                Integer.class
        );
        return (result != null ? result : 0);
    }
}
