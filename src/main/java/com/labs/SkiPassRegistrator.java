package com.labs;

import com.labs.types.SkiPassType;

import java.util.*;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

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

    public Set<SkiPass> getRegisteredSkiPasses() {
        return cardsAndBlockedStatuses.keySet();
    }

    public Set<SkiPass> getBlockedSkiPasses() {
        return cardsAndBlockedStatuses
                .entrySet()
                .stream()
                .filter(map -> map.getValue())
                .map(map -> map.getKey())
                .collect(Collectors.toSet());
    }
}
