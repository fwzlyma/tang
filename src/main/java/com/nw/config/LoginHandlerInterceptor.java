package com.nw.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author : baitao
 * @Time : 2021/8/25 13:07
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录成功之后，应该有用户session
        Object loginUser = request.getSession().getAttribute("LoginUser");
        System.out.println("session" + loginUser);
        if(loginUser == null){
            System.out.println("session" + loginUser);
            request.getRequestDispatcher("/login.html").forward(request, response);
            return false;
        }else {
            System.out.println("session" + loginUser);
            return true;
        }
    }
}
