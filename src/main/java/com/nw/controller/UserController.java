package com.nw.controller;

import com.nw.po.User;
import com.nw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 登录业务
     * @return
     */
    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session){
        User user = userService.getUser(username);
        if(user == null){
            System.out.println("此账号未注册！");
            return "-2";
        }else if(user.getPassword().equals(password)){
            System.out.println("密码正确！");
            session.setAttribute("LoginUser", user);
            return String.valueOf(user.getType());
        }else{
            System.out.println("密码错误！");
            return "-1";
        }
    }

    @RequestMapping("/regist")
    public String register(String username, String password){
        User addUser = new User();
        User user = userService.getUser(username);
//        账号未注册
        if(user == null){
            addUser.setAccount(username);
            addUser.setPassword(password);
            addUser.setType("2");
            addUser.setNickname("用户");
            userService.register(addUser);
            return "0";
        }else {
            return "1";
        }
    }

}
