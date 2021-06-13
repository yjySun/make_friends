package com.yjy.config;

import com.yjy.interceptor.LoginInterceptor;
import com.yjy.interceptor.adminLoginInterceptor;
import com.yjy.interceptor.resetPasswordInterceptor;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionTrackingMode;
import java.util.Collections;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/index","/password","/editPassword","/explore","/feed","/market","/profile","/settingInfo","/setting","/trending","/userMarket");
        registry.addInterceptor(new adminLoginInterceptor()).addPathPatterns("/adminIndex","/adminIndex.*");
        registry.addInterceptor(new resetPasswordInterceptor()).addPathPatterns("/resetPassword");
        super.addInterceptors(registry);
    }


    //静态资源释放
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/","titleImages/","lostImages/");
        super.addResourceHandlers(registry);
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/register").setViewName("part/register");
        registry.addViewController("/").setViewName("part/login");
        registry.addViewController("/login").setViewName("part/login");
        registry.addViewController("/settingInfo").setViewName("part/setting");
        registry.addViewController("/password").setViewName("part/editPassword");
        registry.addViewController("/setting").setViewName("part/setting");
        registry.addViewController("/bacLogin").setViewName("background/adminLogin");
        registry.addViewController("/findPassword").setViewName("part/findPassword");
        registry.addViewController("/resetPassword").setViewName("part/resetPassword");

    }


    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE) );
            }
        };
    }

}
