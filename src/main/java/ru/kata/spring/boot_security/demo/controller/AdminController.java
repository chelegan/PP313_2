package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.entity.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.List;

@Controller
public class AdminController {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleServiceImpl;


    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleServiceImpl) {
        this.userService = userService;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping(value = "/admin/users")
    public String getAllUsers(ModelMap model) {

        model.addAttribute("users", userService.getListOfAllUsers());
        model.addAttribute("user", new User());

        return "adminPage";
    }

    @PostMapping(value = "/admin/addUser")
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam(value = "selectedRoles", required = false) List<String> roles) {

        user.setRoles(roleServiceImpl.getSetOfRoles(roles));
        userService.addUser(user);

        return "redirect:/admin/users";
    }

    @PostMapping("/admin/delete")
    public String deleteUser(@RequestParam("id") Long id) {

        userService.deleteUser(id);

        return "redirect:/admin/users";
    }

    @PostMapping("/admin/update")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "selectedRoles", required = false) List<String> roles,
                             @RequestParam("id") Long id) {

        user.setRoles(roleServiceImpl.getSetOfRoles(roles));
        userService.updateUser(user);

        return "redirect:/admin/users";
    }
}
