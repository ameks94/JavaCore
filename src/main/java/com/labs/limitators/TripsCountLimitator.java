package com.labs.limitators;

import com.labs.SkiPassUsageLimitator;
import com.labs.types.TripsCountType;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by ameks on 01.10.2016.
 */
public class TripsCountLimitator implements SkiPassUsageLimitator {

    private TripsCountType tripsCountType;
    private int tripsWasUsed = 0;

    public TripsCountLimitator(TripsCountType tripsCountType) {
        this.tripsCountType = tripsCountType;
    }

    @Override
    public void useSkiPass() {
        tripsWasUsed++;
    }

    @Override
    public boolean isTripAvailable() {
        if ( tripsCountType.getValue() == tripsWasUsed )
            return false;
        return true;
    }
}
