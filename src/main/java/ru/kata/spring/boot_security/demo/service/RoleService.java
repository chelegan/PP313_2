package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.model.Role;


import java.util.List;
import java.util.Set;

public interface RoleService {
    Role getRoleByName(String roleName);
    Set<Role> getSetOfRoles(List<String> roleNames);
    void deleteAllRoles();
    void saveRole(Role role);

}
