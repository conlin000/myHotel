package com.conlin.hotel.service;

import com.conlin.hotel.dao.OrderDao;
import com.conlin.hotel.dao.UserDao;
import com.conlin.hotel.dto.UserMsg;
import com.conlin.util.RandomUtil;
import com.conlin.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class OrderService {

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

        // 检查预约房间时间段是否合法 (在UserMsgController检验)

        // 生成“临时用户id”
        userMsg.setUserId(RandomUtil.radmonkey(6));
        // 生成“服务码”
        userMsg.setServiceCode(StringUtil.getCharAndNum(8));
        // 生成“临时订单”
        userMsg.setOrderId(StringUtil.getCommonCode(2));
        // 插入user表
        userDao.insertUser(userMsg);
        // 更新“order”表
        orderDao.newOrder(userMsg);

        return userMsg.getServiceCode();
    }
}
