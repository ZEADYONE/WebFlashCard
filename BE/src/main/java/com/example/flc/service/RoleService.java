package com.example.flc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.flc.domain.Role;
import com.example.flc.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAll() {
        return this.roleRepository.findAll();
    }

    public void handleSave(Role role) {
        this.roleRepository.save(role);
    }

    public Role getById(long id) {
        return this.roleRepository.findById(id).orElseThrow(() -> new RuntimeException(" Khong tim thay role"));
    }
}
