package com.example.foodster_foodplanner.screen;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

public class ReminderSetter {

//    Date date = new Date();
//
//    String today =(String) DateFormat.format("dd/MM/yyyy",date);

//    String dayOfTheWeek = (String) DateFormat.format("EEEE", date); // Thursday
//    String day          = (String) DateFormat.format("dd",   date); // 20
//    String monthString  = (String) DateFormat.format("MMM",  date); // Jun
//    String monthNumber  = (String) DateFormat.format("MM",   date); // 06
//    String year         = (String) DateFormat.format("yyyy", date); // 2013

    DayOfWeek[] days = {null, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY};
    LocalDate date = LocalDate.now()
        .with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
    String dd = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH));

}
