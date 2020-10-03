package com.conlin.checkIn;

import com.conlin.response.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class checkInController {

    @Resource
    private CheckInService checkInService;


    @GetMapping("/checkIn")
    public String goCheckIn(){
        return "checkIn";
    }


    @PostMapping("/doCheckIn/{serviceCode}")
    @ResponseBody
    public Response doCheckIn(@PathVariable("serviceCode")String serviceCode){
        System.out.println("========== 入住啦！");
        Response response = checkInService.doCheckIn(serviceCode);
        return response;
    }
}
