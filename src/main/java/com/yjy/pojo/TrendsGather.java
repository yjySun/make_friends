package com.yjy.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Data
public class TrendsGather {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer userId;
    private String username;
    private String titleImages;
    private Integer sex;
    private Integer trendsId;
    private String content;
    private Integer praiseCount;
    private Integer commentCount;
    private Date pushTime;
    private List<Comment> comments;
    private List<String> praiseImages;
    private Integer isPraise; //此篇文章登录用户是否点赞，点赞是1，没点赞是0
    private Integer isComment; //此篇文章登录用户是否评论，评论是1，没评论是0
    private String uuid;
}
