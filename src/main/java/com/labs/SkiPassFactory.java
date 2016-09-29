package com.labs;

import com.labs.skipass.HolidaysSkiPass;
import com.labs.skipass.SeasonSkiPass;
import com.labs.types.SkiPassType;
import com.labs.skipass.WorkDaySkiPass;

/**
 * Created by ameks on 30.09.2016.
 */
public class SkiPassFactory {
    public static SkiPass createSkiPass(SkiPassType type) {
        switch (type) {
            case HOLIDAYS:
                return new HolidaysSkiPass();
            case SEASONS:
                return new SeasonSkiPass();
            case WORK_DAYS:
                return new WorkDaySkiPass();
            default:
                return null;
        }
    }
}
