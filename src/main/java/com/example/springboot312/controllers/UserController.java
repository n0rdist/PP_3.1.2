package com.example.springboot312.controllers;

import com.example.springboot312.model.Role;
import com.example.springboot312.model.User;
import com.example.springboot312.service.RoleService;
import com.example.springboot312.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String mainPage() {
        return "login";
    }

    @GetMapping("/login")
    public String returnLogin() {
        return "login";
    }

    //USER

    @GetMapping("/user")
    public String userPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "userp";
    }

    @GetMapping(value = "/user/{id}")
    public String userById(@PathVariable("id") int id, Model model) {
        User user = userService.readUser(id);
        model.addAttribute("user", user);
        model.addAttribute("role", user.getRoles());
        return "userp";
    }
    


    @DeleteMapping("/user/delete/{id}")
    public String deleteU2(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/login";
    }

    //ADMIN

    //@Secured()
    @GetMapping(value = "/admin")
    public String adminPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("allRoles", user.getRoles());
        model.addAttribute("allUsers", userService.getUsers());
        model.addAttribute("roles",roleService.getRoles());
        return "adminp";
    }

    //создание
    @GetMapping("/admin/new")
    public String saveUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getRoles());///
        return "new";
    }

    @PostMapping("/admin/add")
    public String createUser(@ModelAttribute("user") User user, @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles) {
        Set<Role> roleSet = new HashSet<>();
        for(String role:checkBoxRoles){
            roleSet.add(roleService.getRoleByName(role));
        }
        user.setRoles(roleSet);
        userService.createUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.readUser(id));
        model.addAttribute("roles", roleService.getRoles());///
        return "edit";
    }

    @PostMapping("/admin/edit/{id}")
    public String update(@ModelAttribute("user") User user, @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles) {
        Set<Role> roleSet = new HashSet<>();
        for(String role:checkBoxRoles){
            roleSet.add(roleService.getRoleByName(role));
        }
        user.setRoles(roleSet);
        userService.updateUser(user);
        return "redirect:/admin";
    }


    @DeleteMapping("/admin/delete/{id}")
    public String delete2(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
