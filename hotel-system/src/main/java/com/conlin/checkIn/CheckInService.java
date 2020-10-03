package com.conlin.checkIn;

import com.conlin.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CheckInService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 检测服务码是否存在
     * @param serviceCode
     */
    public boolean checkServiceCode(String serviceCode){
        if (redisTemplate.opsForValue().get(serviceCode) == null){
            return false;
        }
        return true;
    }

    /**
     * 办理入住
     * @param serviceCode
     * @return
     */
    public Response doCheckIn(String serviceCode) {
        if (!checkServiceCode(serviceCode)){
            return Response.error("服务码不存在");
        }
        return null;
    }
}
