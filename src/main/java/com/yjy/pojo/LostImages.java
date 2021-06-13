package com.yjy.pojo;


import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "lostimages")
@NameStyle(Style.normal)
public class LostImages {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String images;
    private String lostUuid;

}
