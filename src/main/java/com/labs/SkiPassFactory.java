package com.labs;


import com.labs.limitators.DaysCountLimitator;
import com.labs.limitators.SeasonRangeLimitator;
import com.labs.limitators.TripsCountLimitator;
import com.labs.types.DaysCountType;
import com.labs.types.SkiPassType;
import com.labs.types.TripsCountType;

import java.time.LocalDate;

/**
 * Created by ameks on 30.09.2016.
 */
public class SkiPassFactory {
    public static SkiPass createSkiPass(SkiPassType type, LocalDate expiredDate, TripsCountType tripsCountType) {
        if (type.equals(SkiPassType.SEASON))
            throw new RuntimeException("TripsCountType is not allowed for Season skipass");
        return new SkiPass(type, expiredDate, new TripsCountLimitator(tripsCountType));
    }

    public static SkiPass createSkiPass(SkiPassType type, LocalDate expiredDate, DaysCountType daysCountType) {
        if (type.equals(SkiPassType.SEASON))
            throw new RuntimeException("DaysCountType is not allowed for Season skipass");
        return new SkiPass(type, expiredDate, new DaysCountLimitator(daysCountType));
    }

    public static SkiPass createSeasonSkiPass(LocalDate expiredDate) {
        return new SkiPass(SkiPassType.SEASON, expiredDate, new SeasonRangeLimitator());
    }
}
