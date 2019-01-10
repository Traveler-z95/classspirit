package com.jinhui.classspirit.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取时间
 */
public class RequestTime {

    /**
     * 获取当前时间
     * @return  时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Date getNowDate() throws ParseException {
        //设置日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = new Date();
        String date = sdf.format(currentTime);
        return sdf.parse(date);
    }

    /**
     * 获取当前时间
     * @return  时间类型 yyyy-MM-dd HH:mm
     */
    public static Date getNowDates() throws ParseException {
        //设置日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date currentTime = new Date();
        String date = sdf.format(currentTime);
        return sdf.parse(date);
    }

    /**
     * 获取当前时间
     * @return 时间类型 yyyy-MM-dd
     * @throws ParseException
     */
    public static Date getNowDateShort() throws ParseException {
        //设置日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentTime = new Date();
        String date = sdf.format(currentTime);
        return sdf.parse(date);
    }

    /**
     * 获取当前时间（时：分：秒）
     * @return 时间类型 HH:mm:ss
     */
    public static Date getDateTime() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String date = sdf.format(currentTime);
        return sdf.parse(date);
    }
    /**
     * 获取当前时间
     * @return 字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = new Date();
        return sdf.format(currentTime);
    }

    /**
     * 获取当前时间
     * @return 字符串格式 yyyy-MM-dd HH:mm
     */
    public static String getStringDates() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date currentTime = new Date();
        return sdf.format(currentTime);
    }

    /**
     * 获取当前时间
     * @return 字符串格式 yyyy-MM-dd
     */
    public static String getStringDateShort() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentTime = new Date();
        return sdf.format(currentTime);
    }

    /**
     * 获取当前时间（时：分：秒）
     * @return 字符串格式 HH:mm:ss
     */
    public static String getStringTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        return sdf.format(currentTime);
    }

    /**
     * 获取当前时间
     * @return  时间类型
     */
    public static Date getNow() {
        return new Date();
    }

    /**
     * 提取一个月中的最后一天
     * @param day
     * @return
     */
    public static Date getLastDate(long day) {
        Date date = new Date();
        long date_last = date.getTime() - 3600000 * 34 * day;
        return new Date(date_last);
    }
}
