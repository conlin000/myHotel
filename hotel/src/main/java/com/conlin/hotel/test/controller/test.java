package com.conlin.hotel.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class test {

    /**
     * 测试工程是否搭建成功
     */
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="test", required = false,
            defaultValue = "World!") String test, Model model) {
        test="conlin";
        model.addAttribute("name", test);
        return "test";
    }

    @GetMapping("/testLayui")
    public String testLayui(){
        return "testLayui";
    }
}