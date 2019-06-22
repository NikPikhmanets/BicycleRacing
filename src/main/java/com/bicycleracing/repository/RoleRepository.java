package com.bicycleracing.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class RoleRepository {

    private final NamedParameterJdbcTemplate parameterJdbcTemplate;

    @Autowired
    public RoleRepository(DataSource dataSource) {
        this.parameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void saveRole(int userId, String role) {
        String sql = "INSERT INTO user_roles(user_id, role) VALUES(:userId, :role)";
        parameterJdbcTemplate.update(
                sql,
                new MapSqlParameterSource()
                        .addValue("userId", userId)
                        .addValue("role", role)
        );
    }

    public List<String> getUserRoles(int userId) {
        String sql = "SELECT user_roles.role FROM user_roles WHERE user_id = :userId";

        return parameterJdbcTemplate.queryForList(
                sql,
                new MapSqlParameterSource("userId", userId),
                String.class
        );
    }
}
