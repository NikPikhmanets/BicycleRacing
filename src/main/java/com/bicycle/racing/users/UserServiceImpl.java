package com.bicycle.racing.users;

import com.bicycle.racing.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }


//    public void saveUser(RegistrationForm form) {
//        User user = getInstanceUser(form);
//        int userId = userRepositoryImpl.saveUser(user);
//        roleService.saveUserRole(userId, "ROLE_USER");
//    }
//
//    private User getInstanceUser(RegistrationForm form) {
//        User user = new User();
//        user.setUsername(form.getUsername());
//        user.setFirstName(form.getFirstName());
//        user.setLastName(form.getLastName());
////        user.setPassword(bCryptPasswordEncoder.encode(form.getPassword()));
//        user.setEnabled(true);
//
//        return user;
//    }
//
//    public User findByUsername(String username) {
//        return userRepositoryImpl.findByUsername(username);
//    }
//
//    public int getUserIdByUserName(String username) {
//        return userRepositoryImpl.getUserIdByUserName(username);
//    }
}
