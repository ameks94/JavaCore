package com.labs;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
                registrator.isSkiPassBlocked(skiPass) ||
                !skiPass.isTripAllowed();

    }

    public List<SkiPass> getRegisteredSkiPasses() {
//        return registrator.
        return Collections.emptyList();
    }
}
