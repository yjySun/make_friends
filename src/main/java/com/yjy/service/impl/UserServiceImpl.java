package com.yjy.service.impl;

import com.yjy.mapper.UserMapper;
import com.yjy.pojo.User;
import com.yjy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //验证账户密码登录
    @Override
    public User getOneUser(String account,String password) {

        User user = null;
        user = userMapper.getOneUser(account, password);
        return user;
    }

    @Override
    public User getEmail(String email){
        User user = null;
        user = userMapper.getEmail(email);
        return user;
    }

    @Override
    public User getNameUser(String username){
        User user = null;
        user = userMapper.getNameUser(username);
        return user;
    }

    @Override
    public User getEditUser(User user) {
        User user1 = null;
        user1 = userMapper.getEditUser(user);
        return user1;
    }

    @Override
    public void addUer(User user) {
        userMapper.insert(user);
    }

    @Override
    public void editUser(User user) throws Exception{
        userMapper.editUser(user);
    }

    @Override
    public Integer editPassword(int id, String password, String confirmPassword) {
        return userMapper.editPassword(id, password, confirmPassword);
    }

    @Override
    public User getUser(int id) {
        User user = null;
        user = userMapper.getUser(id);
        return user;
    }

    @Override
    public void editTitleImages(int id, String titleImages) {
        userMapper.editTitleImages(id, titleImages);
    }

    @Override
    public Integer editTrendsCount(int id) {
        return userMapper.editTrendsCount(id);
    }

    @Override
    public Integer editLostCount(Integer id) {
        return userMapper.editLostCount(id);
    }

    @Override
    public Integer subtractLostCount(Integer id) {
        return userMapper.subtractLostCount(id);
    }

    @Override
    public Integer subtractTrendsCount(Integer id) {
        return userMapper.subtractTrendsCount(id);
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.selectAll();
    }

    @Override
    public Integer resetPassword(Integer id, String resetPassword) {
        return userMapper.resetPassword(id, resetPassword);
    }

    @Override
    public Integer editIsResetPassword(Integer id, Integer isResetPassword) {
        return userMapper.editIsResetPassword(id, isResetPassword);
    }

}
