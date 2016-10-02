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
        if (isSkiPassUnavailable(skiPass)) {
            return false;
        }
        return skiPass.useCardIfAllowed();

    }

    private boolean isSkiPassUnavailable(SkiPass skiPass) {
        return skiPass == null ||
                registrator.isSkiPassBlocked(skiPass);

    }

    public List<SkiPass> getRegisteredSkiPasses() {
//        return registrator.
        return Collections.emptyList();
    }
}
