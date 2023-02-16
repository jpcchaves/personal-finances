package com.jpcchaves.finances.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataConverter {

    public static String convertDateToString(Date date) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(date);

    }

}
