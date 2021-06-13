package com.yjy.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "statistics")
@NameStyle(Style.normal)
public class Statistics {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer allUser;
    private Integer allTrends;
    private Integer allLost;
    private Integer manCount;
    private Double manRatio;

}
