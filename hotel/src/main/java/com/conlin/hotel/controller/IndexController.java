package com.conlin.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(@RequestParam(name = "str", required = false) String str, Model model,
                        HttpServletRequest request, HttpServletResponse response){
        String string = "你好啊啊";
        model.addAttribute("content", string);

        return "index";
    }
}
