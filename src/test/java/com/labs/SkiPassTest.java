package com.labs;


import com.labs.types.DaysCountType;
import com.labs.types.SkiPassType;
import com.labs.types.TripsCountType;
import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.labs.SkipassTestHelper.expiredDateTuesday;
import static com.labs.SkipassTestHelper.nowMondayHalfDay;
import static com.labs.SkipassTestHelper.nowSaturdayHalfDay;

/**
 * Created by ameks on 03.10.2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DateTimeHelper.class)
public class SkiPassTest extends Assert {
    
    @Before
    public void init() throws Exception {
        PowerMockito.mockStatic(DateTimeHelper.class);
        mockCurrDateTime(nowSaturdayHalfDay);
    }
    
    @Test
    public void testSeasonSkiPass() {
        SkiPass seasonSkiPass = SkiPass.createSeasonSkiPass(expiredDateTuesday);
        assertNotNull(seasonSkiPass);
        assertEquals(seasonSkiPass.getType(), SkiPassType.SEASON);
        assertEquals(seasonSkiPass.getExpireDate(), expiredDateTuesday);
        assertNotNull(seasonSkiPass.getId());
        assertTrue(seasonSkiPass.isTripAllowed());

        seasonSkiPass = SkiPass.createSeasonSkiPass(expiredDateTuesday.minusDays(4));
        assertFalse(seasonSkiPass.isTripAllowed());
    }

    @Test
    public void testHolidaySkiPassTrips() {
        testSkiPassTrips(SkiPassType.HOLIDAY);
    }

    @Test
    public void testHolidaySkiPassDays() {
        testSkiPassDays(SkiPassType.HOLIDAY);
    }

    @Test
    public void testWorkdaySkiPassTrips() throws Exception {
        mockCurrDateTime(nowMondayHalfDay);
        testSkiPassTrips(SkiPassType.WORK_DAY);
    }

    @Test
    public void testWorkdaySkiPassDays() throws Exception {
        mockCurrDateTime(nowMondayHalfDay);
        testSkiPassDays(SkiPassType.WORK_DAY);
    }
    
    private void testSkiPassDays(SkiPassType type) {
        SkiPass holidaySkiPassTwoDays = SkiPass.createSkiPass(type, expiredDateTuesday, DaysCountType.TWO_DAYS);
        assertTrue(holidaySkiPassTwoDays.isTripAllowed());

        for (int i = 0; i < 10; i++ ) {
            holidaySkiPassTwoDays.useCardIfAllowed();
        }
        assertTrue(holidaySkiPassTwoDays.isTripAllowed());
    }

    private void testSkiPassTrips(SkiPassType type) {
        SkiPass holidaySkiPassTenTripsCount = SkiPass.createSkiPass(type, expiredDateTuesday, TripsCountType.TEN);
        assertTrue(holidaySkiPassTenTripsCount.isTripAllowed());
        int tripsCount = 0;
        while (holidaySkiPassTenTripsCount.useCardIfAllowed()) {
            tripsCount++;
        }
        assertEquals(tripsCount, TripsCountType.TEN.getValue());
        assertFalse(holidaySkiPassTenTripsCount.isTripAllowed());
    }
    
    private static void  mockCurrDateTime(LocalDateTime dateTime) throws Exception {
        // spy was used for partical mocking
        PowerMockito.spy(DateTimeHelper.class);
        PowerMockito.doReturn(dateTime.toLocalDate()).when(DateTimeHelper.class, "nowDate");
        PowerMockito.doReturn(dateTime.toLocalTime()).when(DateTimeHelper.class, "nowTime");
        PowerMockito.doReturn(dateTime).when(DateTimeHelper.class, "nowDateTime");
        
//        PowerMockito.when(DateTimeHelper.nowDate())
//                .thenReturn(dateTime.toLocalDate());
//        PowerMockito.when(DateTimeHelper.nowTime())
//                .thenReturn(dateTime.toLocalTime());
//        PowerMockito.when(DateTimeHelper.nowDateTime())
//                .thenReturn(dateTime);
    }
    
    
}
