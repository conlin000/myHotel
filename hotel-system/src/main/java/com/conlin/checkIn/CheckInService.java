package com.conlin.checkIn;

import com.alibaba.fastjson.JSON;
import com.conlin.checkIn.entity.User;
import com.conlin.response.Response;
import com.conlin.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class CheckInService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 检测服务码是否存在
     * @param serviceCode
     */
    public boolean checkServiceCode(String serviceCode){
        Set<String> key = redisTemplate.opsForHash().keys("user");
        for (Object i : key){
            if (i.equals(serviceCode)){
                return true;
            }
        }
        return false;
    }

    /**
     * 办理入住
     * @param serviceCode
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Response doCheckIn(String serviceCode) {
        // 检测服务码是否存在
        if (!checkServiceCode(serviceCode)){
            return Response.error("服务码不存在");
        }
        // 从redis抓取此服务码的用户
        String userInRedis = (String) redisTemplate.opsForHash().get("user", serviceCode);
        // json转对象
        User user = JSON.parseObject(userInRedis, User.class);
        if (user.getUserStatus() == 2){
            return Response.success("此服务码已经入住过了！");
        }
        // 设置客户入住时间
        user.setCheckInTime(DateUtil.nowDateTime());
        // 设置客户状态为：入住
        user.setUserStatus(2);
        // 对象转json
        String json = JSON.toJSONString(user);
        // 上传最新的客户信息上redis
        redisTemplate.opsForHash().put("user", user.getServiceCode(), json);
        // 删除redis上的房间预约
        redisTemplate.opsForHash().delete(String.valueOf(user.getRoomId()), "serviceCode:"+user.getServiceCode());

        return Response.success("入住成功！");
    }
}
