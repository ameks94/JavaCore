package com.labs.limitators;

import com.labs.SkiPassUsageLimitator;
import com.labs.types.TripsCountType;

/**
 * Created by ameks on 01.10.2016.
 */
public class TripsCountLimitator implements SkiPassUsageLimitator {

    TripsCountType tripsCountType;



    @Override
    public void useSkiPass() {

    }

    @Override
    public boolean isTripAvailable() {
        return false;
    }
}
