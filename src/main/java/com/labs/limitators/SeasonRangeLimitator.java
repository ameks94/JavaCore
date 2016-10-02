package com.labs.limitators;

import com.labs.SkiPassUsageLimitator;

/**
 * Created by ameks on 01.10.2016.
 */
public class SeasonRangeLimitator implements SkiPassUsageLimitator {

    public SeasonRangeLimitator() {
    }

    @Override
    public void useSkiPass() {
    }

    @Override
    public boolean isTripAvailable() {
        return true;
    }
}
