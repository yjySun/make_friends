package com.yjy.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class resetPasswordInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object resetUser = request.getSession().getAttribute("resetUser");
        if (resetUser == null) {
            request.getRequestDispatcher("/login").forward(request, response);
            return false;
        }
        return true;
    }
}
