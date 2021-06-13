package com.yjy.web;

import com.yjy.pojo.Comment;
import com.yjy.pojo.Praise;
import com.yjy.pojo.Trends;
import com.yjy.pojo.User;
import com.yjy.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class TrendsController {

    @Autowired
    private TrendsServiceImpl trendsService;

    @Autowired
    private PraiseServiceImpl praiseService;

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private StatisticsServiceImpl statisticsService;

    @GetMapping("/addTrends")
    @ResponseBody
    public String addTrends(Trends trends, HttpSession session){
        trends.setPraiseCount(0);
        trends.setCommentCount(0);
        trends.setPushTime(new Date());
        trendsService.addTrends(trends);
        userService.editTrendsCount(trends.getUserId());
        User user = userService.getUser(trends.getUserId());
        session.setAttribute("user",user);

        statisticsService.editAllTrends();

        return "addSuccess";
    }

    @GetMapping("/editTrends")
    @ResponseBody
    public String editTrends(int trendsId, String content){
        Integer integer = trendsService.editTrends(trendsId, content);
        if (integer == 0) {
            return "editFailure";
        }else {
            return "editSuccess";
        }

    }

    @GetMapping("/deleteTrends")
    @ResponseBody
    public String deleteTrends(int trendsId, HttpSession session){
        praiseService.deletePraise(trendsId);
        commentService.deleteComment(trendsId);
        trendsService.deleteTrends(trendsId);

        User oneUser = (User) session.getAttribute("user");
        userService.subtractTrendsCount(oneUser.getId());
        User user = userService.getUser(oneUser.getId());
        session.setAttribute("user",user);
        statisticsService.editAllTrends();

        return "deleteSuccess";

    }

    @GetMapping("/addPraise")
    @ResponseBody
    public String addPraise(Praise praise){
        Praise praise1 = praiseService.getPraise(praise);
        if(praise1 == null){
            praiseService.addPraise(praise);
            trendsService.editPraiseCount(praise.getPraiseTrends());
            return "addSuccess";
        }else{
            return "addFailure";
        }
    }

    @GetMapping("/addComment")
    @ResponseBody
    public String addComment(Comment comment){
        comment.setCommentTime(new Date());
        Integer integer = commentService.addComment(comment);
        Integer integer1 = trendsService.editCommentCount(comment.getTrendsId());
        if(integer == 0 || integer1 == 0){
            return "addFailure";
        }
        return "addSuccess";
    }


}
