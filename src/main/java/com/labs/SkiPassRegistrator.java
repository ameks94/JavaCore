package com.labs;

import com.labs.types.SkiPassType;

import java.util.*;
import java.util.function.BooleanSupplier;

public class SkiPassRegistrator {

    Map<SkiPass, Boolean> cardsAndBlockedStatuses = new HashMap<>();

    public void registerSkiPass(SkiPass skiPass) {
        cardsAndBlockedStatuses.put(skiPass, false);
    }

    public void blockSkiPass(SkiPass skiPass) {
        if (cardsAndBlockedStatuses.containsKey(skiPass)) {
            cardsAndBlockedStatuses.put(skiPass, true);
        } else {
            throw new RuntimeException("Can't block nonexistent skipass.");
        }
    }

    public void unblockSkiPass(SkiPass skiPass) {
        if (cardsAndBlockedStatuses.containsKey(skiPass)) {
            cardsAndBlockedStatuses.put(skiPass, false);
        } else {
            throw new RuntimeException("Can't unblock nonexistent skipass.");
        }
    }

    public boolean isSkiPassBlocked(SkiPass skiPass) {
        return cardsAndBlockedStatuses.get(skiPass);
    }
}
