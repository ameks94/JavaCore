package com.labs.limitators;

import com.labs.SkiPassUsageLimitator;
import com.labs.DateTimeHelper;
import com.labs.types.DaysCountType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ameks on 01.10.2016.
 */
public class DaysCountLimitator implements SkiPassUsageLimitator {

    private DaysCountType daysCountType;
    private List<LocalDate> usedDays = new ArrayList<>();

    public DaysCountLimitator(DaysCountType daysCountType) {
        this.daysCountType = daysCountType;
    }

    @Override
    public void useSkiPass() {
        LocalDate now = DateTimeHelper.nowDate();
        if (usedDays.contains(now)) {
            usedDays.add(now);
        }
    }

    @Override
    public boolean isTripAvailable() {
        LocalDateTime now = DateTimeHelper.nowDateTime();
        // if current time is not allowed for type
        if (!daysCountType.isAllowedTime(now.toLocalTime()))
            return false;
        // all days were used, and last was not today
        if ( usedDays.size() == daysCountType.getDaysCount() && !usedDays.contains(now.toLocalDate()) )
            return false;

        return true;
    }
}
