package com.conlin.hotel.controller;

import com.conlin.hotel.dao.RoomDao;
import com.conlin.hotel.dto.RoomDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ajax")
public class testAjax {
//    @Resource
//    private RoomDao roomDao;
//
//
//    @RequestMapping("/getHome")
//    public List<RoomDTO> getHome(){
//
//        return  roomDao.rooms();
//    }

    @RequestMapping("/getStr")
    @ResponseBody
    public String getStr(){

        String str = "你好啊！";
        return str;
    }
}

