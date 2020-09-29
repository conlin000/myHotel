package com.conlin.hotel.controller;

import com.conlin.hotel.dto.UserMsg;
import com.conlin.hotel.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class DespositAndServiceCode {


    @Resource
    private OrderService orderService;

    /**
     * 跳转到支付定金页面
     * @param roomId
     * @param model
     * @return
     */
    @GetMapping("/payDeposit/{roomId}")
    public String payDeposit(@PathVariable("roomId")int roomId, Model model){
        System.out.println("=================== Get:支付"+roomId+"定金页面");
        model.addAttribute("roomId", roomId);
        return "Deposit&ServiceCode";
    }


    /**
     * 预约房间
     * @param roomId
     * @param userMsg
     * @return
     */
    @PostMapping("/serviceCode/{roomId}")
    @ResponseBody
    public String serviceCode(@PathVariable("roomId")int roomId,UserMsg userMsg){

        System.out.println("=================== Post:支付"+roomId+"定金");
        System.out.println(userMsg.getRoomName());
        // 配置roomId
        userMsg.setRoomId(roomId);
        // 预约房间
        String serviceCode =  orderService.doBook(userMsg);

        return serviceCode;
    }
}
