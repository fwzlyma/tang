package com.nw.controller;

import com.nw.po.User;
import com.nw.repository.UserRepository;
import com.nw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
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
            System.out.println(session.getAttribute("LoginUser"));
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

    @RequestMapping("/updatePass")
    @Transactional(rollbackFor=Exception.class)
    public String updatePassword(HttpSession session,String password,String newpassword){
        User loginUser = (User)session.getAttribute("LoginUser");
        if(newpassword!=null && loginUser.getPassword().equals(password)) {
            int i = userRepository.updateP(newpassword, loginUser.getAccount());
            if(i==1){
                session.removeAttribute("LoginUser");
            }
            return "1";
        }else{
            return "0";
        }
    }


    @RequestMapping("/allUser")
    @ResponseBody
    public Map<String, Object> getAlluser(@RequestParam(required = true)int page, @RequestParam(required = true)int limit){
        System.out.println("来获取用户列表了");
        Map<String, Object> result = new HashMap<String, Object>();
        Pageable pageable = PageRequest.of(page-1,limit);
        result.put("code", 0);
        result.put("msg", "");
        result.put("count",userService.getAllCount());
        result.put("data", userService.findAllUser(pageable));
        return result;
    }
    @RequestMapping(value = "/removeUser")
    @ResponseBody
    public String removeUser(@RequestParam("id")long id){
        boolean result = userService.removeUser(id);
        if(result){
            return "1";
        }else{
            return "0";
        }
    }

    @RequestMapping("/updatestatu")
    @Transactional(rollbackFor=Exception.class)
    public String updateStatu(@RequestParam("id")long id){
        int i = userRepository.updateS(id);
        if(i==1) {
            return "1";
        }else{
            return "0";
        }
    }
    @RequestMapping("/updateOpen")
    @Transactional(rollbackFor=Exception.class)
    public String updateOpen(@RequestParam("id")long id){
        int i = userRepository.updateS(id);
        if(i==1) {
            return "1";
        }else{
            return "0";
        }
    }

}
