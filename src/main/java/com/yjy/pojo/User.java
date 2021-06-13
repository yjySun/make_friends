package com.yjy.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Data
@Table(name = "user")
@NameStyle(Style.normal)
public class User {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Integer trendsCount;
    private Integer focusCount;
    private Integer fansCount;
    private Integer sex;
    private String college;
    private String introduce;
    private String professional;
    private String titleImages;
    private Integer lostCount;
    private Date registerTime;
    private Integer isResetPassword;
}
