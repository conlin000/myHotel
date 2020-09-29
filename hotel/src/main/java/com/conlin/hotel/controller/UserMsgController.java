package com.conlin.hotel.controller;

import com.conlin.hotel.dao.RoomDao;
import com.conlin.hotel.dto.RoomDTO;
import com.conlin.hotel.dto.UserMsg;
import com.conlin.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

@Controller
public class UserMsgController {

    @Resource
    private RoomDao roomDao;

    /**
     * 跳转“用户填写信息”
     *
     */
    @GetMapping("/userMsg/{roomId}")
    public String userMsg(@PathVariable("roomId")int id, Model model){
        System.out.println("=================== Get: 用户填写信息页面");
        // 查询被预定房间信息
        RoomDTO roomDTO = roomDao.getRoom(id);
        //
        model.addAttribute("room", roomDTO);
        return "userMsg";
    }



    @PostMapping("/checkRoom")
    public String checkRoom(UserMsg userMsg){
        // 检查预约房间时间段是否合法

        return null;
    }


}
