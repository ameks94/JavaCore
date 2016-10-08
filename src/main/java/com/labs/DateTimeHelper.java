package com.labs;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

public class DateTimeHelper {
    
    public static LocalDate nowDate() {
        return LocalDate.now();
    }

    public static LocalTime nowTime() {
        return LocalTime.now();
    }

    public static LocalDateTime nowDateTime() {
        return LocalDateTime.now();
    }

    public static boolean holidayChecker(TemporalAccessor date) {

        int dayOfWeekNumber = date.get(ChronoField.DAY_OF_WEEK);
        DayOfWeek dayOfWeek = DayOfWeek.of(dayOfWeekNumber);

        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return true;
        } else {
            return false;
        }
    }
}
