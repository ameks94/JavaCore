package com.labs;

import com.labs.types.DaysCountType;
import java.time.LocalTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DaysCountTypeTest {
    @org.junit.Test
    public void testHALF_DAY_FIRST() {
        assertTrue(DaysCountType.HALF_DAY_FIRST.isAllowedTime(LocalTime.parse("10:25")));
        assertFalse(DaysCountType.HALF_DAY_FIRST.isAllowedTime(LocalTime.parse("13:25")));
    }

    @org.junit.Test
    public void testHALF_DAY_SECOND() {
        assertTrue(DaysCountType.HALF_DAY_SECOND.isAllowedTime(LocalTime.parse("13:25")));
        assertFalse(DaysCountType.HALF_DAY_SECOND.isAllowedTime(LocalTime.parse("18:25")));
    }

    @org.junit.Test
    public void test_ONE_DAY_TWO_DAYS_FIVE_DAYS() {
        assertTrue(DaysCountType.ONE_DAY.isAllowedTime(LocalTime.parse("13:25")));
        assertTrue(DaysCountType.TWO_DAYS.getDaysCount() == 2);
        assertTrue(DaysCountType.FIVE_DAYS.getEndTime().equals(LocalTime.parse("17:00")));
    }

}
