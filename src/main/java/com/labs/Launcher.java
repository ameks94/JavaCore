package com.labs;

import com.labs.types.DaysCountType;
import com.labs.types.SkiPassType;

import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class Launcher
{
    public static void main( String[] args )
    {
        SkiPass skiPass = SkiPass.createSeasonSkiPass(LocalDate.now());
        SkiPass skiPass1 = SkiPass.createSkiPass(SkiPassType.WORK_DAY, LocalDate.now(), DaysCountType.TWO_DAYS);
    }
}
