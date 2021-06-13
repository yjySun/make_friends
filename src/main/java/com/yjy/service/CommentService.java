package com.yjy.service;

import com.yjy.pojo.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAllComment(int trendsId);

    void deleteComment(int trendsId);

    void editCommentImages(int userId, String userImages);

    Integer addComment(Comment comment);

    Comment getComment(Comment comment);

}
