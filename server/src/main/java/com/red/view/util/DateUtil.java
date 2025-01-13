package com.red.view.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static boolean isDateString(String value) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            dateFormat.parse(value);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    public static Date parseDateString(String value) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateFormat.parse(value);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + value);
        }
    }

    public static String parseYYYYMMDDHHMMSS(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format: " + date.toString());
        }
    }
}
