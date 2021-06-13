package com.yjy.web;

import com.yjy.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String Logout(Model model, HttpSession session){
        User user = (User)session.getAttribute("user");
        session.removeAttribute("user");
        session.invalidate();

        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
        String date = sdf.format(new Date());
        System.out.println(date+"登出用户为："+user.getUsername());
        System.out.println();
        return "redirect:login";
    }
}
