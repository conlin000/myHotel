package com.conlin.login;

import com.conlin.response.Response;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    public Response login(String acct, String pwd){

        User user = userDao.login(acct, pwd);

        if(user == null){
            return Response.error("登录失败");
        }
        return Response.success("登录成功", user);
    }
}
