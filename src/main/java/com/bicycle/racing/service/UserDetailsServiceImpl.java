package com.bicycle.racing.service;

import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl /*implements UserDetailsService*/ {

//    private final UserRepository userRepository;
//    private final RoleService roleService;
//
//    @Autowired
//    public UserDetailsServiceImpl(UserRepository userRepository, RoleService roleService) {
//        this.userRepository = userRepository;
//        this.roleService = roleService;
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String username) {
//        User user = userRepository.findByUsername(username);
//
//        if (user == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        List<String> roleNames = roleService.getUserRoles(user.getId());
//        List<GrantedAuthority> grantList = new ArrayList<>();
//
//        if (roleNames != null) {
//            for (String role : roleNames) {
//                GrantedAuthority authority = new SimpleGrantedAuthority(role);
//                grantList.add(authority);
//            }
//        }
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                grantList
//        );
//    }
}
