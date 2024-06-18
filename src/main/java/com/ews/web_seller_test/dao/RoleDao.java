package com.ews.web_seller_test.dao;

import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.model.Role;

import java.util.List;

public interface RoleDao {
    void insertRole(Role role);

    void editRole(Role newRole);

    void deleteRole(int id);

    Role getRole(int id);

    List<Role> getAllRole();

    List<Role> searchRole(String roleName);
}
