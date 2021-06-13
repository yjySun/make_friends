package com.yjy.mapper;

import com.yjy.pojo.Comment;
import tk.mybatis.mapper.common.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper extends Mapper<Comment> {

    /*获取单个动态的所有评论*/
    List<Comment> getAllComment(int trendsId);

    void deleteComment(int trendsId);

    void editCommentImages(@Param(value = "userId")int userId, @Param(value = "userImages")String userImages);

    Comment getComment(@Param(value = "comment") Comment comment);
}
