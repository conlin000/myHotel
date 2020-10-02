package com.conlin.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RedisTemplate redisTemplate;

    public boolean checkBookDate(String roomId, String userBookStart, String userBookEnd){
        // 如果roomId房间没有预约直接返回
        if (redisTemplate.opsForHash().size(roomId) == 0){
            return true;
        }
        // 摘取roomId 的 redis数据
        List<String> list = redisTemplate.opsForHash().values(roomId);

        for (int i=0; i<list.size(); i++){
            // 拆分预约时间字符串
            String start = list.get(i).split("/")[0];
            String end = list.get(i).split("/")[1];
            // 校验”预约开始时间“
            if (userBookStart != null){
                if ((userBookStart.compareTo(start) == 0 || userBookStart.compareTo(start) > 0)
                        && (userBookStart.compareTo(end) == 0 || userBookStart.compareTo(end) < 0)){
                    return false;
                }
            }
            // 校验”预约结束时间“
            else if(userBookEnd != null){
                if((userBookEnd.compareTo(start) == 0 || userBookEnd.compareTo(start) > 0)
                        && (userBookEnd.compareTo(end) == 0 || userBookEnd.compareTo(end) < 0)){
                    return false;
                }
            }
        }
        return true;
    }

}
