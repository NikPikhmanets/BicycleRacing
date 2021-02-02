package com.bicycle.racing.users;

import com.bicycle.racing.users.model.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
}
