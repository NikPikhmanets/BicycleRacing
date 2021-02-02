package com.bicycle.racing.service;

import com.bicycle.racing.model.User;
import com.bicycle.racing.model.form.RegistrationForm;
import com.bicycle.racing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleService roleService
//            ,
//                       BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleService = roleService;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void saveUser(RegistrationForm form) {
        User user = getInstanceUser(form);
        int userId = userRepository.saveUser(user);
        roleService.saveUserRole(userId, "ROLE_USER");
    }

    private User getInstanceUser(RegistrationForm form) {
        User user = new User();
        user.setUsername(form.getUsername());
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
//        user.setPassword(bCryptPasswordEncoder.encode(form.getPassword()));
        user.setEnabled(true);

        return user;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public int getUserIdByUserName(String username) {
        return userRepository.getUserIdByUserName(username);
    }
}
