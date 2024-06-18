package com.ews.web_seller_test.service.impl;

import com.ews.web_seller_test.dao.UserDao;
import com.ews.web_seller_test.dao.impl.UserDaoImpl;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.UserService;

import java.io.File;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public void editUser(User newUser) {
        User oldUser = userDao.getUser(newUser.getId());

        oldUser.setFull_name(newUser.getFull_name());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setPhone(newUser.getPhone());
        oldUser.setAddress(newUser.getAddress());
        oldUser.setUsername(newUser.getUsername());
        oldUser.setPassword(newUser.getPassword());
        oldUser.setGender(newUser.getGender());
        oldUser.setRole(newUser.getRole());
        oldUser.setAvatar(newUser.getAvatar());

        userDao.editUser(oldUser);
    }

//    @Override
//    public void edit(User newUser) {
//        User oldUser = userDao.get(newUser.getId());
//
//        oldUser.setEmail(newUser.getEmail());
//        oldUser.setUsername(newUser.getUsername());
//        oldUser.setPassword(newUser.getPassword());
//        oldUser.setRoleId(newUser.getRoleId());
//        if (newUser.getAvatar() != null) {
//            // XOA ANH CU DI
//            String fileName = oldUser.getAvatar();
//            final String dir = "C:\\Users\\mai vien\\eclipse-workspace\\UNIFY\\upload";
//            File file = new File(dir + "/" + fileName);
//            if (file.exists()) {
//                file.delete();
//            }
//            // THEM ANH MOI
//            oldUser.setAvatar(newUser.getAvatar());
//        }
//
//        userDao.edit(oldUser);
//    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    public User getUser(String username) {
        return userDao.getUser(username);
    }

    @Override
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    public User loginUser(String username, String password) {
        User user = this.getUser(username);
        if(user != null && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public boolean registerUser(String username,String password, String email, String full_name, String phone, String address, String gender) {
        if(userDao.checkExistUsername(username)) {
            return false;
        }
        userDao.insertUser(new User(full_name, email, phone, address, username, password, gender));
        return true;
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public List<User> searchUser(String keyword) {
        return userDao.searchUser(keyword);
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }
}
