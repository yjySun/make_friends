package com.yjy.web.background;

import com.yjy.pojo.*;
import com.yjy.service.impl.OnlineServiceImpl;
import com.yjy.service.impl.StatisticsServiceImpl;
import com.yjy.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class adminIndexController {

    @Autowired
    private StatisticsServiceImpl statisticsService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private OnlineServiceImpl onlineService;

    @GetMapping("/adminIndex")
    public String adminIndex(HttpSession session){
        Statistics statistics = statisticsService.getStatistics();
        RegisterStatistics registerStatistics = statisticsService.getRegisterMonth();
        Integer registerCount = statisticsService.getRegisterCount();

        Double growthRatio = 0.00;
        List<User> allUser = userService.getAllUser();
        if (allUser.size() != 0){
            growthRatio = statisticsService.getGrowthRatio();
        }

        registerStatistics.setRegisterCount(registerCount);
        registerStatistics.setGrowthRatio(growthRatio);

        List<Online> allOnline = onlineService.getAllOnline();

        session.setAttribute("allOnline", allOnline);
        session.setAttribute("statistics", statistics);
        session.setAttribute("registerStatistics", registerStatistics);
        return "background/adminIndex";
    }

}
