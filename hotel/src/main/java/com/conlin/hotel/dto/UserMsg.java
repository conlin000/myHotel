package com.conlin.hotel.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserMsg implements Serializable {

    //客户的id
    private String userId;
    //房间的id
    private int roomId;
    //订单id 客户预定或者入住后就会预先生成一个订单，退房后结账
    private String orderId;
    //客户名称
    private String userName;
    //性别
    private int sex;
    //手机号
    private String phone;
    //身份证
    private String idCard;
    //客户级别：0-普通客户；1-VIP；2-钻石VIP；3-至尊VIP
    private int level;
    //服务码 客户通过服务码进行入住
    private String serviceCode;
    //VIP标志：0-不是VIP；1-VIP
    private int isVip;
    // 预约时间起
    private String bookTime;
    // 预约时间止
    private String endTime;
    // 备注
    private String comment;
    // 定金
    private float deposit;
    // 房间名称
    private String roomName;
    // 房间级别：1-普通；2-豪华；3-总统套房
    private String roomLevel;
    // 房间类型：1-单人间；2-双人间；3-总统套房
    private String roomType;
    // 房间版本号
    private int version;



}
