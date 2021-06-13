package com.yjy.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@WebListener
public class UserStatisticsListener implements HttpSessionListener, ServletContextListener {

    public static int count = 0;

    public static List<HttpSession> list = new LinkedList<HttpSession>();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
        String date = sdf.format(new Date());
        HttpSession session = se.getSession();
        list.add(session);
        count++;
        System.out.println(date+"创建了一个session对象:"+count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
        String date = sdf.format(new Date());
        list.remove(se.getSession());
        System.out.println(date+"销毁了一个session对象");

        if(count > 0){
            count--;
        }
        System.out.println(date+"在线人数："+count);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("contextInitialized..............");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed......");
    }

}

