package com.conlin.hotel.controller;

import com.conlin.hotel.dao.RoomDao;
import com.conlin.hotel.dto.RoomDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private RoomDao roomDao;

    @GetMapping("/ma-hotel")
    public String index(Model model){
        List<RoomDTO> roomList = roomDao.rooms();
        model.addAttribute("roomList",roomList);
        return "index";
    }
}
