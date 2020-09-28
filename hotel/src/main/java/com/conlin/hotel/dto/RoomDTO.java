package com.conlin.hotel.dto;

import lombok.Data;

@Data
public class RoomDTO {
    // 房间的id
    private int roomId;
    // 房间名称
    private String roomName;
    // 楼层
    private int floor;
    // 房间号
    private int roomNum;
    // 房间介绍
    private String roomContent;
    // 房间级别：1-普通；2-豪华；3-总统套房
    private String roomLevel;
    // 房间类型：1-单人间；2-双人间；3-总统套房
    private String roomType;
    // 房间状态：0-不可用；1-空闲；2-已被预约；3-已被入住（用户不可见）
    private int roomStatus;
    // 被使用标志：0-被使用；1-未被使用
    private int isUsed;
    // 版本号
    private int version;
}
