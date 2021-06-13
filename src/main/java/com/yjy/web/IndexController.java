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

@Controller
public class IndexController {

    @Autowired
    private TrendsServiceImpl trendsService;

    @Autowired
    private PraiseServiceImpl praiseService;

    @Autowired
    private CommentServiceImpl commentService;

    @GetMapping("/index")
    public String indexTrends(Model model, HttpSession session){

        User user = (User) session.getAttribute("user");
        List<TrendsGather> userTrends = trendsService.getUserTrends(user.getId());
        for (int i = 0; i < userTrends.size(); i++) {
            TrendsGather trendsGather = userTrends.get(i);
            /*每条动态的所有评论*/
            List<Comment> allComment = null;
            allComment = commentService.getAllComment(trendsGather.getTrendsId());
            userTrends.get(i).setComments(allComment);

            /*显示点赞的前三个图片*/
            List<String> praiseImages = null;
            praiseImages = praiseService.getPraiseImages(trendsGather.getTrendsId());
            userTrends.get(i).setPraiseImages(praiseImages);

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
        }
        session.setAttribute("userTrends",userTrends);
        return "part/feed";
    }
}
