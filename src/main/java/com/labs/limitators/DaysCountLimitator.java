package com.labs.limitators;

import com.labs.SkiPassUsageLimitator;
import com.labs.types.DaysCountType;

/**
 * Created by ameks on 01.10.2016.
 */
public class DaysCountLimitator implements SkiPassUsageLimitator {

    DaysCountType daysCountType;

    @Override
    public void useSkiPass() {

    }

    @Override
    public boolean isTripAvailable() {
        return false;
    }
}
