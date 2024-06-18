package com.ews.web_seller_test.dao.impl;

import com.ews.web_seller_test.dao.MyDAO;
import com.ews.web_seller_test.dao.RoleDao;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.model.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl extends MyDAO implements RoleDao {
    @Override
    public void insertRole(Role role) {
        String sql = "INSERT INTO Role (role_name, description) VALUES (?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, role.getRole_name());
            ps.setString(2, role.getDescription());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editRole(Role newRole) {
        String sql = "UPDATE Role SET role_name=?, description=?, updated_at=GETDATE() WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, newRole.getRole_name());
            ps.setString(2, newRole.getDescription());
            ps.setInt(3, newRole.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRole(int id) {
        String sql = "DELETE FROM Role WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Role getRole(int id) {
        Role role = null;
        String sql = "SELECT * FROM Role WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                role = new Role();
                role.setId(rs.getInt("id"));
                role.setRole_name(rs.getString("role_name"));
                role.setDescription(rs.getString("description"));
                role.setCreated_at(rs.getTimestamp("created_at"));
                role.setUpdated_at(rs.getTimestamp("updated_at"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public List<Role> getAllRole() {
        List<Role> roles = new ArrayList<Role>();
        String sql = "SELECT * FROM Role";
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setRole_name(rs.getString("role_name"));
                role.setDescription(rs.getString("description"));
                role.setCreated_at(rs.getTimestamp("created_at"));
                role.setUpdated_at(rs.getTimestamp("updated_at"));
                roles.add(role);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    @Override
    public List<Role> searchRole(String roleName) {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * FROM Role WHERE role_name LIKE ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + roleName + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setRole_name(rs.getString("role_name"));
                role.setDescription(rs.getString("description"));
                role.setCreated_at(rs.getTimestamp("created_at"));
                role.setUpdated_at(rs.getTimestamp("updated_at"));
                roles.add(role);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }
}
