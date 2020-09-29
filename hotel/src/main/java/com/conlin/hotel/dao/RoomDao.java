package com.conlin.hotel.dao;

import com.conlin.hotel.dto.RoomDTO;
import com.conlin.hotel.dto.UserMsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoomDao {

    // 主页房间查询
    List<RoomDTO> rooms();
    // 查询房间 by id
    RoomDTO getRoom(@Param("id")int id);
    // 预约房间--更新
    int bookRoom(UserMsg userMsg);
}
