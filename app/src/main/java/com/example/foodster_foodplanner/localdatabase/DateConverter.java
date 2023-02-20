package com.example.foodster_foodplanner.localdatabase;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static Date fromDate(Long milliseconds) {
        if (milliseconds != null) {
            return new Date(milliseconds);
        } else return null;
    }

    @TypeConverter
    public static Long toDate(Date date) {
        if (date != null) {
            return date.getTime();
        } else return null;
    }
}
