package com.jinhui.classspirit.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类型转换
 */
public class TypeConversion {

    /**
     * 字符串转换为时间类型 长时间格式：yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static Date stringToDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        return sdf.parse(date, pos);
    }

    /**
     * 短字符串转换为时间类型
     * @param date
     * @return
     */
    public static Date stringToDateShort(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        return sdf.parse(date, pos);
    }

    /**
     * 时间类型转换为字符串
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 短时间类型转换为字符串
     * @param date
     * @return
     */
    public static String dateToStringShort(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
