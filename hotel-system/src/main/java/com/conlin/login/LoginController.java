package com.conlin.login;

import com.conlin.response.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class LoginController {

    @Resource
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        System.out.println("=========== 登录啦!");
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Response login(String acct, String pwd){
       Response response = userService.login(acct, pwd);
       return response;
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }
}
