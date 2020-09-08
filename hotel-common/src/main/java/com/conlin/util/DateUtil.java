package com.conlin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String dateToStrLong(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        String dateStr = format.format(date);
        return dateStr;
    }

    public static void main(String[] args) {

    }
}
