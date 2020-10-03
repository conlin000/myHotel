package com.conlin.hotel.service;

import com.alibaba.fastjson.JSON;
import com.conlin.hotel.dao.OrderDao;
import com.conlin.hotel.dao.UserDao;
import com.conlin.hotel.dto.UserMsg;
import com.conlin.hotel.entity.User;
import com.conlin.util.DateUtil;
import com.conlin.util.RandomUtil;
import com.conlin.util.StringUtil;
import org.springframework.beans.BeanUtils;
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
        User user = new User();
        BeanUtils.copyProperties(userMsg, user);
        // 检查预约房间时间段是否合法 (在UserMsgController检验了)
        // 生成“临时用户id”
        user.setUserId(RandomUtil.radmonkey(6));
        // 生成“服务码”
        user.setServiceCode(StringUtil.getCharAndNum(8));
        // 生成“临时订单”
        user.setOrderId(StringUtil.getCommonCode(2));
        // 设置 客户 为预约状态
        user.setUserStatus(1);
        // 将房间预约时间存入redis
        // 拼接预约时间
        String bookDate = user.getBookTime()+"/"+user.getEndTime();
        // 将房间预约时间存入redis
        redisTemplate.opsForHash().put(String.valueOf(user.getRoomId()), "serviceCode:"+user.getServiceCode(), bookDate);
        // 设置过期时间为 最新的 预约结束时间的后一天
        redisTemplate.expireAt(String.valueOf(user.getRoomId()),DateUtil.DateForProOrNext("next",user.getEndTime()));
        // 将用户信息存入redis
        // 以Hash类型存入Redis
        String json = JSON.toJSONString(user);
        redisTemplate.opsForHash().put("user", user.getServiceCode(), json);
        // 设置过期时间
        redisTemplate.expireAt("user",DateUtil.DateForProOrNext("next",user.getEndTime()));
        // 创建临时订单“order”表
//        orderDao.newOrder(user);

        return user.getServiceCode();
    }
}
