package com.yjy.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "lost")
@NameStyle(Style.normal)
public class Lost {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String lostName;
    private String contactName;
    private String contactPhone;
    private String description;
    private Integer lookCount;
    private Integer userId;
    private String uuid;
    private Date pushTime;
}