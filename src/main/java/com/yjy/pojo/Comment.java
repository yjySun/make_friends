package com.yjy.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "comment")
@NameStyle(Style.normal)
public class Comment {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @Column(name = "commentContent")
    private String commentContent;
    @Column(name = "trendsId")
    private Integer trendsId;
    @Column(name = "userId")
    private Integer userId;
    @Column(name = "userImages")
    private String userImages;
    @Column(name = "commentTime")
    private Date commentTime;
}
