package com.bicycle.racing.users.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String avatar;
    private String email;
    private String phone_number;
    private boolean enabled;
    private LocalDateTime birthday;
    private LocalDateTime createdAt;
}
