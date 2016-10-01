package com.labs;

import com.labs.skipass.HolidaySkiPass;
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
            case HOLIDAY:
                return new HolidaySkiPass();
            case SEASON:
                return new SeasonSkiPass();
            case WORK_DAY:
                return new WorkDaySkiPass();
            default:
                return null;
        }
    }
}
