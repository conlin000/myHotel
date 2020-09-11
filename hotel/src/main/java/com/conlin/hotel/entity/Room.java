package com.conlin.hotel.entity;

import lombok.Data;

@Data
public class Room {
    // 房间的id
    private int roomId;

    // 使用房间客户的id（可以多个房客）
    private String userId;

    // 房间名称
    private String roomName;

    // 楼层
    private int floor;

    // 房间号
    private int roomNum;

    // 房间介绍
    private String roomContent;

    // 房间级别：1-普通；2-豪华；3-总统套房
    private int roomLevel;

    // 房间类型：1-单人间；2-双人间；3-总统套房
    private int roomType;

    // 房间状态：0-不可用；1-空闲；2-已被预定；3-已被入住（用户不可见）
    private int roomStatus;

    // 被使用标志：0-被使用；1-未被使用
    private int isUsed;

    // 预定开始时间
    private String bookTime;

    // 预定结束时间
    private String endTime;

    // 入住时间
    private String checkInTime;

    // 退房时间
    private String checkOutTime;

    // 删除标志：0-未删除；1-已删除
    private int isDeleted;

    // 创建时间
    private String gmtCreate;

    // 修改时间
    private String gmtModified;

    // 版本号
    private int version;

}
