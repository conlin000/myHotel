package com.conlin.hotel.controller;

import com.conlin.hotel.dao.RoomDao;
import com.conlin.hotel.dto.RoomDTO;
import com.conlin.hotel.dto.UserMsg;
import com.conlin.hotel.service.RoomService;
import com.conlin.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserMsgController {

    @Resource
    private RoomDao roomDao;

    @Resource
    private RoomService roomService;

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

    /**
     * 检测房间预约开始时间是否可用
     * @param roomId
     * @param date
     * @return
     */

    @PostMapping("/checkRoom/{roomId}/bookStart")
    @ResponseBody
    public String checkBookStart(@PathVariable("roomId")int roomId, String date){
        // 转String
        String room = String.valueOf(roomId);
        // 检查预约房间开始日期是否被预约
        if (!roomService.checkBookDate(room, date, null)){
            String msg = "预约开始时间：" + date + "已被他人预约";
            return msg;
        }
        return null;
    }

    /**
     * 检测房间预约结束时间是否可用
     * @param roomId
     * @param date
     * @return
     */
    @PostMapping("/checkRoom/{roomId}/bookEnd")
    @ResponseBody
    public String checkBookEnd(@PathVariable("roomId")int roomId, String date){
        // 转String
        String room = String.valueOf(roomId);
        // 检查预约房间结束日期是否被预约
        if (!roomService.checkBookDate(room, null, date)){
            String msg = "预约结束时间：" + date + "已被他人预约";
            return msg;
        }
        return null;
    }


}
