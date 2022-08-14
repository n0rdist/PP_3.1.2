package com.example.springboot312.service;

import com.example.springboot312.model.Role;

import java.util.List;

public interface RoleService {
    void createRole(Role role);

    void updateRole(Role role);

    void removeRole(int id);

    List<Role> getRoles();

    Role getRoleByName(String name);

}