package com.yjy.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;

@Data
public class CommentGather {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String commentContent;
    private Integer trendsId;
    private Integer userId;
    private String username;

}
