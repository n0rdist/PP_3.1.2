package com.example.springboot312.dao;

import com.example.springboot312.model.Role;

import java.util.List;

public interface RoleDao {
    void createRole(Role role);

    void updateRole(Role role);

    void removeRole(int id);

    List<Role> getRoles();

    Role getRoleByName(String name);

}