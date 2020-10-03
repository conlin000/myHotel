package com.conlin.hotel.service;

import com.alibaba.fastjson.JSON;
import com.conlin.hotel.dao.OrderDao;
import com.conlin.hotel.dao.UserDao;
import com.conlin.hotel.dto.UserMsg;
import com.conlin.util.DateUtil;
import com.conlin.util.RandomUtil;
import com.conlin.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class OrderService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private UserDao userDao;

    @Resource
    private OrderDao orderDao;


    /**
     * 预约房间
     * @param userMsg
     */
    @Transactional(rollbackFor = Exception.class)
    public String doBook(UserMsg userMsg){

        // 检查预约房间时间段是否合法 (在UserMsgController检验了)

        // 生成“临时用户id”
        userMsg.setUserId(RandomUtil.radmonkey(6));
        // 生成“服务码”
        userMsg.setServiceCode(StringUtil.getCharAndNum(8));
        // 生成“临时订单”
        userMsg.setOrderId(StringUtil.getCommonCode(2));
        // 将房间预约时间存入redis
        // 拼接预约时间
        String bookDate = userMsg.getBookTime()+"/"+userMsg.getEndTime();
        try {
            // 将房间预约时间存入redis
            redisTemplate.opsForHash().put(String.valueOf(userMsg.getRoomId()), "serviceCode:"+userMsg.getServiceCode(), bookDate);
            // 设置过期时间为 最新的 预约结束时间的后一天
            redisTemplate.expireAt(String.valueOf(userMsg.getRoomId()),DateUtil.DateForProOrNext("next",userMsg.getEndTime()));
            // 将用户信息存入redis
            if (userMsg.getIsVip() == 0){
                // 普通用户预约 以String类型存入Redis
//            String json = JSON.toJSONString(userMsg);
//            redisTemplate.opsForValue().set(userMsg.getServiceCode(),json);
//            // 设置过期时间
//            redisTemplate.expireAt("userServiceCode:"+userMsg.getServiceCode(),DateUtil.DateForProOrNext("next",userMsg.getEndTime()));
                // 普通用户预约
//                String json = JSON.toJSONString(userMsg);
                redisTemplate.opsForHash().put("user", userMsg.getServiceCode(), userMsg);
                // 设置过期时间 以Hash类型存入Redis
                redisTemplate.expireAt("userServiceCode:"+userMsg.getServiceCode(),DateUtil.DateForProOrNext("next",userMsg.getEndTime()));

            }
        }catch (Exception e){
            throw e;
        }

        // 更新“order”表
//        orderDao.newOrder(userMsg);

        return userMsg.getServiceCode();
    }
}
