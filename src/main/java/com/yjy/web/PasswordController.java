package com.yjy.web;

import com.sun.mail.util.MailSSLSocketFactory;
import com.yjy.pojo.User;
import com.yjy.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.UUID;

@Controller
public class PasswordController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/editPassword")
    @ResponseBody
    public String editPassword(int id, String password, String confirmPassword, HttpSession session){
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        confirmPassword = DigestUtils.md5DigestAsHex(confirmPassword.getBytes(StandardCharsets.UTF_8));
        Integer integer = userService.editPassword(id, password, confirmPassword);
        if(integer != 0){
            User editUser = userService.getUser(id);
            session.setAttribute("user", editUser);
            return "editSuccess";
        }else {
            return "editFailure";
        }
    }

    @PostMapping("/resetUserPassword")
    @ResponseBody
    public String resetUserPassword(String resetPassword, HttpSession session){
        Integer resetUserId = (Integer)session.getAttribute("resetUser");
        resetPassword= DigestUtils.md5DigestAsHex(resetPassword.getBytes(StandardCharsets.UTF_8));
        Integer integer = userService.resetPassword(resetUserId, resetPassword);
        Integer integer1 = userService.editIsResetPassword(resetUserId, 0);
        if (integer == 0 || integer1 == 0){
            return "resetFailure";
        }
        session.removeAttribute("resetUser");
        return "resetSuccess";
    }

    @PostMapping("/emailPassword")
    @ResponseBody
    public String findPassword( String email, HttpSession httpSession) throws GeneralSecurityException, MessagingException {

        User user = userService.getEmail(email);
        if(user == null){
            return "notExists";
        }
        String newPassword = UUID.randomUUID() + "";
        String MD5Password = DigestUtils.md5DigestAsHex(newPassword.getBytes(StandardCharsets.UTF_8));

        userService.editPassword(user.getId(), user.getPassword(), MD5Password);
        userService.editIsResetPassword(user.getId(), 1);
        String Info = "sendSuccess";

        //创建一个配置文件并保存
        Properties properties = new Properties();

        properties.setProperty("mail.host","smtp.qq.com");

        properties.setProperty("mail.transport.protocol","smtp");

        properties.setProperty("mail.smtp.auth","true");


        //QQ存在一个特性设置SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);

        //创建一个session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("840893902@qq.com","zeqnexmoqdupbbdb");
            }
        });

        //开启debug模式
        session.setDebug(true);

        //获取连接对象
        Transport transport = session.getTransport();

        //连接服务器
        transport.connect("smtp.qq.com","840893902@qq.com","zeqnexmoqdupbbdb");

        //创建邮件对象
        MimeMessage mimeMessage = new MimeMessage(session);

        //邮件发送人
        mimeMessage.setFrom(new InternetAddress("840893902@qq.com"));

        //邮件接收人
        mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(email));

        //邮件标题
        mimeMessage.setSubject("Instello校园交友系统密码找回");

        //邮件内容
        mimeMessage.setContent("您的密码已经修改为："+newPassword+"<br>"+"登录后请您重新修改密码。","text/html;charset=UTF-8");

        //发送邮件
        try {
            transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
        } catch (MessagingException e) {
            Info =  "sendFailure";
            e.printStackTrace();
        }

        //关闭连接
        transport.close();

        return Info;

    }


}
