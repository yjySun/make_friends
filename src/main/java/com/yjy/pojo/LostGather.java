package com.yjy.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "lost")
@NameStyle(Style.normal)
public class LostGather {
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
    private String username;
    @Column(name = "pushtime")
    private Date pushTime;
    private List<String> lostImages;

}
