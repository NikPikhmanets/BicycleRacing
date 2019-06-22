package com.bicycleracing.service;

import com.bicycleracing.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public void saveUserRole(int userId, String role) {
        roleRepository.saveRole(userId, role);
    }

    public List<String> getUserRoles(int userId) {
        return roleRepository.getUserRoles(userId);
    }
}
