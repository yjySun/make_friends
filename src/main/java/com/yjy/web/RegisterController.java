package com.yjy.web;

import com.sun.mail.util.MailSSLSocketFactory;
import com.yjy.pojo.User;
import com.yjy.service.impl.StatisticsServiceImpl;
import com.yjy.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

@Controller
public class RegisterController {

    public String result="";

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private StatisticsServiceImpl statisticsService;

    @PostMapping("/registerUser")
    @ResponseBody
    public String register(String username, String email, String password, String code, Model model, HttpSession session){
        String info = null;
        if(!result.equals(code)){
            info = "codeError";
        } else if(userService.getNameUser(username) != null){
            info = "nameExists";
        }else{
            password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setSex(1);
            user.setRegisterTime(new Date());
            user.setFansCount(0);
            user.setLostCount(0);
            user.setFocusCount(0);
            user.setTrendsCount(0);
            user.setIsResetPassword(0);
            userService.addUer(user);
            statisticsService.editAllUser();
            statisticsService.editManCount();
            statisticsService.editManRatio();
            info = "addSuccess";
        }
        return info;
    }

    @GetMapping("/sendEmail")
    @ResponseBody
    public String sendEmail(String email) throws GeneralSecurityException, MessagingException {
        if(userService.getEmail(email) != null){
            return "emailExists";
        }
        result = "";
        String Info = "sendSuccess";
        Random random = new Random();
        for (int i=0;i<6;i++) {
            result+=random.nextInt(10);
        }

        //?????????????????????????????????
        Properties properties = new Properties();

        properties.setProperty("mail.host","smtp.qq.com");

        properties.setProperty("mail.transport.protocol","smtp");

        properties.setProperty("mail.smtp.auth","true");


        //QQ????????????????????????SSL??????
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);

        //????????????session??????
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("840893902@qq.com","zeqnexmoqdupbbdb");
            }
        });

        //??????debug??????
        session.setDebug(true);

        //??????????????????
        Transport transport = session.getTransport();

        //???????????????
        transport.connect("smtp.qq.com","840893902@qq.com","zeqnexmoqdupbbdb");

        //??????????????????
        MimeMessage mimeMessage = new MimeMessage(session);

        //???????????????
        mimeMessage.setFrom(new InternetAddress("840893902@qq.com"));

        //???????????????
        mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(email));

        //????????????
        mimeMessage.setSubject("Instello???????????????????????????????????????");

        //????????????
        mimeMessage.setContent("????????????????????????:"+result,"text/html;charset=UTF-8");

        //????????????
        try {
            transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
        } catch (MessagingException e) {
            Info =  "sendFailure";
            e.printStackTrace();
        }

        //????????????
        transport.close();

        System.out.println("??????sendEmail???????????????"+result);

        return Info;
    }
}
