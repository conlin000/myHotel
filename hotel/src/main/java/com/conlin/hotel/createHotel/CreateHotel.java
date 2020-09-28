package com.conlin.hotel.createHotel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CreateHotel {

    @Resource
    private HotelDao hotelDao;

    /**
     * 创建酒店
     */

    @RequestMapping("/createHotel")
    public void createHotel(){
        Room room = new Room();
        for (int i = 2; i < 9; i++) {

            for (int j = 1; j < 11; j++) {

                room.setRoomId(i * 100 + j);
                room.setFloor(i);
                room.setRoomNum(j);
                room.setRoomName(i * 100 + j + "号房");
                room.setRoomLevel(1);
                room.setRoomType(1);
                if ((i >= 3 && i < 6 || i == 7) && j >= 6) {
                    room.setRoomType(2);
                }
                if (i >= 6) {
                    room.setRoomLevel(2);
                }
                if (i == 8) {
                    room.setRoomLevel(3);
                    room.setRoomType(3);
                }

                // 写入数据库
                hotelDao.hotel(room);
                System.out.print(" |" + room.getRoomName() + "-" + room.getRoomLevel() + "-" + room.getRoomType() + "人房" + "| ");
            }
            System.out.println();
        }
    }

}
