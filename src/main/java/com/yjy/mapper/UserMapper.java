package com.yjy.mapper;

import com.yjy.pojo.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {

    User getOneUser(@Param(value = "account") String account, @Param(value = "password")String password);

    User getEmail(@Param(value = "email") String email);

    User getNameUser(@Param(value = "username") String username);

    void editUser(@Param("user")User user);

    User getEditUser(@Param("user")User user);

    Integer editPassword(@Param(value = "id")int id, @Param(value = "password")String password, @Param(value = "confirmPassword")String confirmPassword);

    User getUser(@Param(value = "id") int id);

    void editTitleImages(@Param(value = "id") int id,@Param(value = "titleImages")String titleImages);

    Integer editTrendsCount(@Param(value = "id")Integer id);

    Integer editLostCount(@Param(value = "id")Integer id);

    Integer subtractLostCount(@Param(value = "id")Integer id);

    Integer subtractTrendsCount(@Param(value = "id")Integer id);

    Integer resetPassword(@Param(value = "id")Integer id, @Param(value = "resetPassword")String resetPassword);

    Integer editIsResetPassword(@Param(value = "id")Integer id, @Param(value = "isResetPassword")Integer isResetPassword);
}
