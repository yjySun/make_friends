package com.yjy.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "praise")
@NameStyle(Style.normal)
public class Praise {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @Column(name = "praiseUser")
    private Integer praiseUser;
    @Column(name = "praiseTrends")
    private Integer praiseTrends;
}
