package com.conlin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController

public class MyRedis {

    @Autowired
    private RedisTemplate redisTemplate;

    Jedis jedis = new Jedis("localhost");

    @RequestMapping("/myRedis")
    public String myRedis() {
        System.out.println("进入redis");
        String serviceCode[]={"ASDX1154","QQSLA2684","OSNX3365"};

        Map<String,String> map = new HashMap<>();
        map.put("serviceCode:"+serviceCode[0],"2020-10-01/2020-10-02");
        map.put("serviceCode:"+serviceCode[1],"2020-10-03/2020-10-05");
        map.put("serviceCode:"+serviceCode[2],"2020-10-06/2020-10-07");

        redisTemplate.opsForHash().putAll("102",map);
        System.out.println("上传redis");

        Map<String,String> result = redisTemplate.opsForHash().entries("102");
        System.out.println("result:" + result);


        redisTemplate.opsForHash().put("102","serviceCode:0000xx","2020-10-09/2020-10-10");
        List<String> list = redisTemplate.opsForHash().values("102");
        System.out.println("list:"+list);
        System.out.println("listSize:"+list.size());

        ///////////////////////////////////
        String myStart = "2020-10-08";
        String myEnd = "2020-10-09";
        for (int i =0; i<list.size(); i++){
            String start = list.get(i).split("/")[0];
            String end = list.get(i).split("/")[1];

            //System.out.println(myStart.compareTo(start));

            if ((myStart.compareTo(start) == 0 || myStart.compareTo(start) > 0)
                    && (myStart.compareTo(end) == 0 || myStart.compareTo(end) < 0)){
                System.out.println("myStart时间："+myStart+"已被预约");
            } //else { System.out.println("nope");}
            if((myEnd.compareTo(start) == 0 || myEnd.compareTo(start) > 0)
                    && (myEnd.compareTo(end) == 0 || myStart.compareTo(end) < 0)){
                System.out.println("myEnd时间：" + myEnd + "已被预约");
            } //else { System.out.println("nope");}
        }

//        redisTemplate.opsForHash().put("102","serviceCode:"+serviceCode[0],"2020-10-01/2020-10-02");
//        redisTemplate.opsForHash().put("102","serviceCode:"+serviceCode[1],"2020-10-02/2020-10-05");
//        redisTemplate.opsForHash().put("102","serviceCode:"+serviceCode[2],"2020-10-05/2020-10-06");


        return "OK";
    }

}
