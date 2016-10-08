package com.labs;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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

    public Set<SkiPass> getRegisteredSkiPasses() {
        return registrator.getRegisteredSkiPasses();
    }
}
