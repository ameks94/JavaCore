package com.labs.limitators;

import com.labs.SkiPassUsageLimitator;
import com.labs.types.DaysCountType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ameks on 01.10.2016.
 */
public class DaysCountLimitator implements SkiPassUsageLimitator {

    private DaysCountType daysCountType;
    private List<LocalDate> usedDays = new ArrayList<>();
    private int skiPassWasUsed = 0;

    public DaysCountLimitator(DaysCountType daysCountType) {
        this.daysCountType = daysCountType;
    }

    @Override
    public void useSkiPass() {
        if (!isTripAvailable())
            throw new RuntimeException("Trip is not allowed.");

        LocalDate now = LocalDate.now();
        if (usedDays.contains(now)) {
            usedDays.add(now);
        }
        skiPassWasUsed++;
    }

    @Override
    public boolean isTripAvailable() {
        LocalDateTime now = LocalDateTime.now();
        // if current time is not allowed for type
        if (!daysCountType.isAllowedTime(now.toLocalTime()))
            return false;
        // all days were used, and last was not today
        if ( usedDays.size() == daysCountType.getDaysCount() && !usedDays.contains(now.toLocalDate()) )
            return false;

        return true;
    }
}
