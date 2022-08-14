package com.example.springboot312.config;

import com.example.springboot312.model.Role;
import com.example.springboot312.model.User;
import com.example.springboot312.service.RoleService;
import com.example.springboot312.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class InitUsers {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public InitUsers(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Bean
    @PostConstruct
    public void addSomeUsers() {
        User admin = new User();
        User user = new User();


        roleService.createRole(new Role("ROLE_ADMIN"));
        roleService.createRole(new Role("ROLE_USER"));

        Set<Role> rolesForAdmin = new HashSet<>();
        rolesForAdmin.add(roleService.getRoleByName("ROLE_ADMIN"));
        rolesForAdmin.add(roleService.getRoleByName("ROLE_USER"));

        Set<Role> rolesForUser = new HashSet<>();
        rolesForUser.add(roleService.getRoleByName("ROLE_USER"));
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setEmail("admin@mail.ru");
        admin.setAge(20);
        admin.setRoles(rolesForAdmin);
        userService.createUser(admin);

        user.setUsername("user");
        user.setPassword("user");
        user.setEmail("user@mail.ru");
        user.setAge(20);
        user.setRoles(rolesForUser);
        userService.createUser(user);
    }
}
