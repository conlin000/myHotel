package com.conlin.hotel.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
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
    // 用户状态：0-无；1-预约；2-入住
    private int userStatus;
    // 预约时间起
    private String bookTime;
    // 预约时间止
    private String endTime;
    // 入住时间
    private String checkInTime;
    // 退房时间
    private String checkOutTime;
    // 备注
    private String comment;
    // 定金
    private float deposit;
    // 房间版本号
    private int version;

}
