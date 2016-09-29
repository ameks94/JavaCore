package com.labs;

import java.time.LocalDate;

/**
 * Created by ameks on 26.09.2016.
 */
public class PayGate {

    private SkiPassRegistrator registrator;

    public PayGate(SkiPassRegistrator registrator) {
        this.registrator = registrator;
    }

    public boolean checkAndUseCard(SkiPass skiPass) {
        if (isSkiPassBad(skiPass)) {
            return false;
        }
        skiPass.useCard();
        return true;
    }

    private boolean isSkiPassBad(SkiPass skiPass) {
        return skiPass == null ||
                skiPass.isDateExpired() ||
                registrator.isSkiPassBlocked(skiPass) ||
                !skiPass.isTripExists();
    }
}
