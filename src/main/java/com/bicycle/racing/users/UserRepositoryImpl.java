package com.bicycle.racing.users;

import com.bicycle.racing.users.mapper.UserRowMapper;
import com.bicycle.racing.users.model.User;
import com.bicycle.racing.utils.ResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    private static final String FIND_ALL_USERS = "findAllUsers.sql";

    private final NamedParameterJdbcTemplate parameterJdbcTemplate;

    @Autowired
    public UserRepositoryImpl(DataSource dataSource) {
        this.parameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<User> findAll() {
        String sql = ResourceUtils.resourceAsString(UserRepository.class, FIND_ALL_USERS);

        try {
            return parameterJdbcTemplate.query(sql, new UserRowMapper());
        } catch (DataAccessException ex) {
            return Collections.emptyList();
        }
    }

//    public int saveUser(User user) {
//        String sql = "INSERT INTO users (username, first_name, last_name, password, enabled) " +
//                "VALUES(:username, :firstName, :lastName, :password, :enabled)";
//        KeyHolder holder = new GeneratedKeyHolder();
//        SqlParameterSource namedParameters = new MapSqlParameterSource()
//                .addValue("username", user.getUsername())
//                .addValue("firstName", user.getFirstName())
//                .addValue("lastName", user.getLastName())
//                .addValue("password", user.getPassword())
//                .addValue("enabled", user.isEnabled());
//        parameterJdbcTemplate.update(sql, namedParameters, holder, new String[]{"id"});
//
//        return Objects.requireNonNull(holder.getKey()).intValue();
//    }
//
//    public User findByUsername(String username) {
//        String sql = "SELECT * FROM users WHERE users.username = :username";
//        List<User> users = parameterJdbcTemplate.query(
//                sql,
//                new MapSqlParameterSource("username", username),
//                userRowMapper
//        );
//        return users.isEmpty() ? null : users.get(0);
//    }
//
//    public int getUserIdByUserName(String username) {
//        String sql = "SELECT id FROM users WHERE users.username = :username";
//        Integer result = parameterJdbcTemplate.queryForObject(
//                sql,
//                new MapSqlParameterSource("username", username),
//                Integer.class
//        );
//        return (result != null ? result : 0);
//    }
}
