package com.yjy.service;

import com.yjy.pojo.User;

import java.util.List;

public interface UserService {

    //账号验证
    User getOneUser(String account,String password);

    User getEmail(String email);

    User getNameUser(String username);

    User getEditUser(User user);

    void addUer(User user);

    void editUser(User user) throws Exception;

    Integer editPassword(int id, String password, String confirmPassword);

    User getUser(int id);

    void editTitleImages(int id, String titleImages);

    Integer editTrendsCount(int id);

    Integer editLostCount(Integer id);

    Integer subtractLostCount(Integer id);

    Integer subtractTrendsCount(Integer id);

    List<User> getAllUser();

    Integer resetPassword(Integer id, String resetPassword);

    Integer editIsResetPassword(Integer id, Integer isResetPassword);

}
