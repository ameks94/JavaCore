package com.labs.skipass;

import com.labs.SkiPass;

/**
 * Created by ameks on 30.09.2016.
 */
public class HolidaysSkiPass extends SkiPass {
    private int tripNumber;

    @Override
    public boolean isTripExists() {
        return false;
    }

    @Override
    public void useCard() {

    }
}
