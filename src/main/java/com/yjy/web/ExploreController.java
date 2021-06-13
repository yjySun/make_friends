package com.yjy.web;

import com.yjy.pojo.Comment;
import com.yjy.pojo.Praise;
import com.yjy.pojo.TrendsGather;
import com.yjy.pojo.User;
import com.yjy.service.impl.CommentServiceImpl;
import com.yjy.service.impl.PraiseServiceImpl;
import com.yjy.service.impl.TrendsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
public class ExploreController {

    @Autowired
    private TrendsServiceImpl trendsService;

    @Autowired
    private PraiseServiceImpl praiseService;

    @Autowired
    private CommentServiceImpl commentService;

    @GetMapping("/explore")
    public String explore(Model model, HttpSession session){
        User user = (User)session.getAttribute("user");
        List<TrendsGather> allTrends = trendsService.getAllTrends();
        for (int i = 0; i < allTrends.size(); i++) {
            TrendsGather trendsGather = allTrends.get(i);
            String uuid = "a"+UUID.randomUUID();
            trendsGather.setUuid(uuid);
            /*每条动态的所有评论*/
            List<Comment> allComment = null;
            allComment = commentService.getAllComment(trendsGather.getTrendsId());
            allTrends.get(i).setComments(allComment);

            /*显示点赞的前三个图片*/
            List<String> praiseImages = null;
            praiseImages = praiseService.getPraiseImages(trendsGather.getTrendsId());
            allTrends.get(i).setPraiseImages(praiseImages);

            /*登录的用户是否已经点赞此篇动态*/
            Praise praise = new Praise();
            praise.setPraiseTrends(trendsGather.getTrendsId());
            praise.setPraiseUser(user.getId());
            Praise praise1 = praiseService.getPraise(praise);
            if(praise1 == null){
                trendsGather.setIsPraise(0);
            }else{
                trendsGather.setIsPraise(1);
            }

            /*登录的用户是否已经评论此篇动态*/
            Comment comment = new Comment();
            comment.setTrendsId(trendsGather.getTrendsId());
            comment.setUserId(user.getId());
            Comment comment1 = commentService.getComment(comment);
            if(comment1 == null){
                trendsGather.setIsComment(0);
            }else{
                trendsGather.setIsComment(1);
            }
        }
        session.setAttribute("allTrends",allTrends);
        return "part/explore";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session){
        return "part/profile";
    }
}
