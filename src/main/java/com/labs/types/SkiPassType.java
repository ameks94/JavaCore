package com.labs.types;

import com.labs.DateTimeHelper;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by ameks on 29.09.2016.
 */
public enum SkiPassType {
    HOLIDAY,
    WORK_DAY,
    SEASON;

    public boolean isSkiPassValidToday() {
        switch (this) {
            case HOLIDAY:
                return DateTimeHelper.nowDate().query(DateTimeHelper::holidayChecker);
            case SEASON:
                return true;
            case WORK_DAY:
                return !DateTimeHelper.nowDate().query(DateTimeHelper::holidayChecker);
        }
        return false;
    }
}
