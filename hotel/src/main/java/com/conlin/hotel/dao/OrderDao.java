package com.conlin.hotel.dao;

import com.conlin.hotel.dto.UserMsg;
import com.conlin.hotel.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao {
    // 预约房间--插入新订单
    int newOrder(User user);
}
