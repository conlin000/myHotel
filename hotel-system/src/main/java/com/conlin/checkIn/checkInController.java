package com.conlin.checkIn;

import com.conlin.response.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class checkInController {

    @GetMapping("/checkIn")
    public String goCheckIn(){
        return "checkIn";
    }


    @PostMapping("/doCheckIn/{serviceCode}")
    public Response doCheckIn(@PathVariable("serviceCode")String serviceCode){
        return null;
    }
}
