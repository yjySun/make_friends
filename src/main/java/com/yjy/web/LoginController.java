package com.yjy.web;

import com.yjy.config.UserStatisticsListener;
import com.yjy.pojo.Online;
import com.yjy.pojo.User;
import com.yjy.service.impl.OnlineServiceImpl;
import com.yjy.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ListIterator;

@Controller
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private OnlineServiceImpl onlineService;

    @PostMapping("/userLogin")
    @ResponseBody
    public String Login(String account, String password, String remember, HttpSession session, HttpServletResponse response){
        String MD5Password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        User oneUser = userService.getOneUser(account, MD5Password);
        if (oneUser == null){
            session.invalidate();
            return "loginFailure";
        } else if (oneUser.getIsResetPassword() == 1){
            session.setAttribute("resetUser", oneUser.getId());
            return "resetPassword";
        } else {
            System.out.println("连接的sessionId为："+session.getId());
            ListIterator<HttpSession> it = UserStatisticsListener.list.listIterator();
            while (it.hasNext()) {
                HttpSession httpSession = it.next();
                User user = (User) httpSession.getAttribute("user");
                if (user != null){
                    System.out.println("遍历的user为："+user);
                    if ((account.equals(user.getUsername())  || account.equals(user.getEmail())) && !session.getId().equals(httpSession.getId())) {
                        httpSession.invalidate();
                        break;
                    }
                }
            }

            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
            String dateTime = sdf.format(new Date());
            System.out.println(dateTime+"登录用户为:"+oneUser.getUsername());
            System.out.println(dateTime+"在线人数："+UserStatisticsListener.count);
            System.out.println();

            Integer personCount = UserStatisticsListener.count;
            Date date = new Date();
            SimpleDateFormat hh = new SimpleDateFormat("HH");
            String format = hh.format(date);
            Integer onlineHour = Integer.parseInt(format);
            Online online = onlineService.getOnline(onlineHour);

            if (online == null){
                Online newOnline = new Online();
                newOnline.setPersonCount(personCount);
                newOnline.setOnlineHour(onlineHour);
                onlineService.addOnline(newOnline);
            }else {
                if (online.getPersonCount() < personCount){
                    onlineService.editOnlineCount(personCount, onlineHour);
                }
            }

            //设置cookie
            Cookie nameCookie = new Cookie("name", account);
            Cookie passwordCookie = new Cookie("password", password);
            if (("true").equals(remember)){
                nameCookie.setMaxAge(60 * 60 * 24 * 7);
                passwordCookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(nameCookie);
                response.addCookie(passwordCookie);
            }else {
                nameCookie.setMaxAge(0);
                passwordCookie.setMaxAge(0);
                response.addCookie(nameCookie);
                response.addCookie(passwordCookie);
            }

            session.setAttribute("user",oneUser);
            return "loginSuccess";
        }

    }
}
