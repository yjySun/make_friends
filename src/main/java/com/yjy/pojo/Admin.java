package com.yjy.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "admin")
@NameStyle(Style.normal)
public class Admin {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String adminName;
    private String password;

}
