package com.conlin.hotel.dao;

import com.conlin.hotel.dto.RoomDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomDao {

    /**
     * 主页-查询房间
     * @return
     */
    List<RoomDTO> rooms();
}
