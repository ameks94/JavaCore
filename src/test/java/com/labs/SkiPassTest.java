package com.labs;


import com.labs.types.DaysCountType;
import com.labs.types.SkiPassType;
import com.labs.types.TripsCountType;
import org.junit.*;

import java.time.LocalDate;

/**
 * Created by ameks on 03.10.2016.
 */
public class SkiPassTest extends Assert {
    private static final LocalDate expiredDateTomorrow = LocalDate.now().plusDays(1);
    private static final LocalDate expiredDateYesterday = LocalDate.now().minusDays(1);

    @org.junit.Test
    public void testSeasonSkiPass() {
        SkiPass seasonSkiPass = SkiPass.createSeasonSkiPass(expiredDateTomorrow);
        assertNotNull(seasonSkiPass);
        assertEquals(seasonSkiPass.getType(), SkiPassType.SEASON);
        assertEquals(seasonSkiPass.getExpireDate(), expiredDateTomorrow);
        assertNotNull(seasonSkiPass.getId());
        assertTrue(seasonSkiPass.isTripAllowed());

        seasonSkiPass = SkiPass.createSeasonSkiPass(expiredDateYesterday);
        assertFalse(seasonSkiPass.isTripAllowed());
    }

    @org.junit.Test
    public void testHolidaySkiPass() {
        SkiPass holidaySkiPassTenTripsCount = SkiPass.createSkiPass(SkiPassType.HOLIDAY, expiredDateTomorrow, TripsCountType.TEN);
        assertTrue(holidaySkiPassTenTripsCount.isTripAllowed());
        int tripsCount = 0;
        while (holidaySkiPassTenTripsCount.useCardIfAllowed()) {
            tripsCount++;
        }
        assertEquals(tripsCount, TripsCountType.TEN.getValue());
        assertFalse(holidaySkiPassTenTripsCount.isTripAllowed());

//        SkiPass holidaySkiPassTwoDays = SkiPass.createSkiPass(SkiPassType.HOLIDAY, expiredDateTomorrow.plusDays(10), DaysCountType.TWO_DAYS);
//        assertTrue(holidaySkiPassTwoDays.isTripAllowed());
//        int tripsCount = 0;
//        while (holidaySkiPassTenTripsCount.useCardIfAllowed()) {
//            tripsCount++;
//        }
//        assertEquals(tripsCount, TripsCountType.TEN.getValue());
//        assertFalse(holidaySkiPassTenTripsCount.isTripAllowed());
    }
}
