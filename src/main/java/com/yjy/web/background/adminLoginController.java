package com.yjy.web.background;

import com.yjy.config.UserStatisticsListener;
import com.yjy.pojo.Admin;
import com.yjy.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

@Controller
public class adminLoginController {

    @Autowired
    private AdminServiceImpl adminService;

    @PostMapping("/adminLogin")
    @ResponseBody
    public String adminLogin(Admin admin,HttpSession session){
        System.out.println("admin:同时在线人数："+UserStatisticsListener.count);
        String MD5Password = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes(StandardCharsets.UTF_8));
        admin.setPassword(MD5Password);
        Admin admin1 = adminService.getAdmin(admin);
        if(admin1 == null){
            return "loginFailure";
        }
        session.setAttribute("admin",admin1);
        return "loginSuccess";
    }

    @GetMapping("/adminLoginOut")
    public String adminLoginOut(HttpSession session){
        session.removeAttribute("admin");
        session.invalidate();
        return "redirect:bacLogin";

    }
}
