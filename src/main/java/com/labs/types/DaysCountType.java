package com.labs.types;

import java.time.LocalTime;

/**
 * Created by ameks on 01.10.2016.
 */
public enum DaysCountType {

    HALF_DAY_FIRST(1, parseTime("09:00"), parseTime("13:00")),
    HALF_DAY_SECOND(1, parseTime("13:00"), parseTime("17:00")),
    ONE_DAY(1, parseTime("09:00"), parseTime("17:00")),
    TWO_DAYS(2, parseTime("09:00"), parseTime("17:00")),
    FIVE_DAYS(3, parseTime("09:00"), parseTime("17:00"));

    private int daysCount;
    private LocalTime startTime;
    private LocalTime endTime;

    DaysCountType(final int daysCount, LocalTime startTime, LocalTime endTime) {
        this.daysCount = daysCount;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isAllowedTime(LocalTime time) {
        return time.isAfter(startTime) && time.isBefore(endTime);
    }

    private static LocalTime parseTime(String time) {
        return LocalTime.parse(time);
    }

    public int getDaysCount() {
        return daysCount;
    }

    public void setDaysCount(int daysCount) {
        this.daysCount = daysCount;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
