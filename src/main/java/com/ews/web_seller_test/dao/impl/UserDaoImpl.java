package com.ews.web_seller_test.dao.impl;

import com.ews.web_seller_test.dao.UserDao;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.dao.MyDAO;
import com.ews.web_seller_test.service.RoleService;
import com.ews.web_seller_test.service.impl.RoleServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends MyDAO implements UserDao {
    RoleService roleService = new RoleServiceImpl();
    @Override
    public void insertUser(User user) {
//        int roleId = 0;
        xSql = "INSERT INTO User (full_name, email, phone, address, username, password, gender, avatar, role_id, created_at, updated_at) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(xSql);

            ps.setString(1, user.getFull_name());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getUsername());
            ps.setString(6, user.getPassword());
            ps.setString(7, user.getGender());
            ps.setString(8, "default.jpg");
//            try {
//                if(user.getRole().getId() == 1) {
//                    roleId = 1;
//                } else {
//                    roleId = 2;
//                }
//            } catch (Exception e) {
//                roleId = 2;
//            }
//            ps.setInt(9, roleId);
            ps.setInt(9, 2);
            ps.setDate(10, new java.sql.Date(System.currentTimeMillis()));
            ps.setTimestamp(11, new java.sql.Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editUser(User user) {
        xSql = "UPDATE [User] SET full_name= ?, email = ?, phone = ?, address = ?, username = ?, password = ?, gender = ?, avatar = ?, role_id = ?, updated_at = GETDATE() WHERE id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, user.getFull_name());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getUsername());
            ps.setString(6, user.getPassword());
            ps.setString(7, user.getGender());
            ps.setString(8, user.getAvatar());
            ps.setInt(9, user.getRole().getId());
            ps.setInt(10, user.getId());
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        xSql = "DELETE FROM [User] WHERE id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(String username) {
        xSql = "SELECT * FROM [User] WHERE username = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFull_name(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setAvatar(rs.getString("avatar"));
                user.setRole(roleService.getRole(rs.getInt("role_id")));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUser(int id) {
        xSql = "SELECT * FROM `User` WHERE id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFull_name(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setAvatar(rs.getString("avatar"));
                user.setRole(roleService.getRole(rs.getInt("role_id")));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();
        xSql = "SELECT * FROM User";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFull_name(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setAvatar(rs.getString("avatar"));
                user.setRole(roleService.getRole(rs.getInt("role_id")));
                user.setCreated_at(rs.getDate("created_at"));
                user.setUpdated_at(rs.getDate("updated_at"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public List<User> searchUser(String username) {
        List<User> userList = new ArrayList<>();
        xSql = "SELECT * FROM [User] WHERE username LIKE ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + username + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFull_name(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setAvatar(rs.getString("avatar"));
                user.setRole(roleService.getRole(rs.getInt("role_id")));
                user.setCreated_at(rs.getDate("created_at"));
                user.setUpdated_at(rs.getDate("updated_at"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public boolean checkExistEmail(String email) {
        xSql = "SELECT * FROM [User] WHERE email = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ignored) {
        }
        return false;
    }

    @Override
    public boolean checkExistUsername(String username) {
        xSql = "SELECT * FROM [User] WHERE username = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ignored) {
        }
        return false;
    }
}