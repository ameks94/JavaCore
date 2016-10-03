package com.labs;

import com.labs.types.DaysCountType;
import junit.framework.Assert;
import org.junit.*;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by ameks on 04.10.2016.
 */
public class DaysCountTypeTest extends Assert {
    @org.junit.Test
    public void testHALF_DAY_FIRST() {
        assertTrue(DaysCountType.HALF_DAY_FIRST.isAllowedTime(LocalTime.of(10, 25)));
        assertFalse(DaysCountType.HALF_DAY_FIRST.isAllowedTime(LocalTime.of(12, 25)));
    }

    @org.junit.Test
    public void testHALF_DAY_SECOND() {
        assertTrue(DaysCountType.HALF_DAY_SECOND.isAllowedTime(LocalTime.of(12, 25)));
        assertFalse(DaysCountType.HALF_DAY_SECOND.isAllowedTime(LocalTime.of(18, 25)));
    }

    @org.junit.Test
    public void test_ONE_DAY_TWO_DAYS_FIVE_DAYS() {
        assertTrue(DaysCountType.ONE_DAY.isAllowedTime(LocalTime.of(13, 25)));
        assertTrue(DaysCountType.TWO_DAYS.getDaysCount() == 2);
        assertTrue(DaysCountType.FIVE_DAYS.getEndTime().equals(LocalDate.parse("17:00:00")));
    }

}
