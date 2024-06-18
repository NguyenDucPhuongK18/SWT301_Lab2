package com.ews.web_seller_test.service.impl;

import com.ews.web_seller_test.dao.RoleDao;
import com.ews.web_seller_test.dao.impl.RoleDaoImpl;
import com.ews.web_seller_test.model.Role;
import com.ews.web_seller_test.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    RoleDao roleDao = new RoleDaoImpl();


    @Override
    public void insertRole(Role role) {
        roleDao.insertRole(role);
    }

    @Override
    public void editRole(Role newRole) {
        roleDao.editRole(newRole);
    }

    @Override
    public void deleteRole(int id) {
        roleDao.deleteRole(id);
    }

    @Override
    public Role getRole(int id) {
        return roleDao.getRole(id);
    }

    @Override
    public List<Role> getAllRole() {
        return roleDao.getAllRole();
    }

    @Override
    public List<Role> searchRole(String roleName) {
        return roleDao.searchRole(roleName);
    }
}
