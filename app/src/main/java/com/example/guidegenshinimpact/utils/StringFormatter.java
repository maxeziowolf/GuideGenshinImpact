package com.example.guidegenshinimpact.utils;

import android.util.Log;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StringFormatter {

    public static String upperCaseFirst(String val) {
        char[] arr = val.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
    }

    public static String formattingDate(String dateText ){
        String patternInitial = "yyyy-MM-dd";
        String patternFinal = "dd-MMMM";
        Locale locale = new Locale("es","MX");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternInitial);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(patternFinal,locale);

        try {
            Date date = simpleDateFormat.parse(dateText);
            String dateFormatted = simpleDateFormat2.format(date);
            dateFormatted = dateFormatted.replace("-"," de ");
            return dateFormatted;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Desconocida";

    }
}
