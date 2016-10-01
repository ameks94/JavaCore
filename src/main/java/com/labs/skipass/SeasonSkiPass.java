package com.labs.skipass;

import com.labs.SkiPass;
import com.labs.SkiPassUsageLimitator;
import com.labs.types.SkiPassType;

import java.time.LocalDate;

/**
 * Created by ameks on 30.09.2016.
 */
public class SeasonSkiPass extends SkiPass {

    public SeasonSkiPass(SkiPassUsageLimitator limitator, LocalDate expireDate) {
        super(SkiPassType.SEASON, expireDate, limitator);
    }
}
