package com.labs.skipass;

import com.labs.SkiPass;
import com.labs.SkiPassUsageLimitator;
import com.labs.types.SkiPassType;

import java.time.LocalDate;

/**
 * Created by ameks on 30.09.2016.
 */
public class HolidaySkiPass extends SkiPass {

    public HolidaySkiPass(LocalDate expireDate, SkiPassUsageLimitator limitator) {
        super(SkiPassType.HOLIDAY, expireDate, limitator);
    }
}
