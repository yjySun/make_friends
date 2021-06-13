package com.yjy.service.impl;

import com.yjy.mapper.CommentMapper;
import com.yjy.pojo.Comment;
import com.yjy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> getAllComment(int trendsId) {
        return commentMapper.getAllComment(trendsId);
    }

    @Override
    public void editCommentImages(int userId, String userImages) {
        commentMapper.editCommentImages(userId, userImages);
    }

    @Override
    public void deleteComment(int trendsId) {
        commentMapper.deleteComment(trendsId);
    }

    @Override
    public Integer addComment(Comment comment){
        return commentMapper.insert(comment);
    }

    @Override
    public Comment getComment(Comment comment) {
        return commentMapper.getComment(comment);
    }

}
