package com.conlin.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserMsgController {

    @GetMapping("/userMsg")
    public String userMsg(){
        return "userMsg";
    }
}
