package com.conlin.hotel.dao;

import com.conlin.hotel.dto.UserMsg;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    // 预约房间--插入客户
    int insertUser(UserMsg userMsg);
}
