package com.labs;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by ameks on 09.10.2016.
 */
public class SkipassTestHelper {
    //11.10.2016
    public static final LocalDate expiredDateTuesday = LocalDate.of(2016, 10, 11);
    //08.10.2016 12:30
    public static final LocalDateTime nowSaturdayHalfDay = LocalDateTime.of( 2016, 10, 8, 12, 30 );
    //10.10.2016 12:30
    public static final LocalDateTime nowMondayHalfDay = LocalDateTime.of( 2016, 10, 10, 12, 30 );
}
