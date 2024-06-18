package com.ews.web_seller_test.service;

import com.ews.web_seller_test.model.Role;

import java.util.List;

public interface RoleService {
    void insertRole(Role role);

    void editRole(Role newRole);

    void deleteRole(int id);

    Role getRole(int id);

    List<Role> getAllRole();

    List<Role> searchRole(String roleName);
}
