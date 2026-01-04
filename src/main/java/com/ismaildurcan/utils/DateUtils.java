package com.ismaildurcan.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getCurrentDate(Date date) {
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

}
