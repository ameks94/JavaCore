package com.labs.types;

/**
 * Created by ameks on 30.09.2016.
 */
public enum TripsCountType {
    TEN(10),
    TWENTY(20),
    FIFTY(50),
    HUNDRED(100);

    private final int value;

    TripsCountType(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
