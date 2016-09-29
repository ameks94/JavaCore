package com.labs;

import com.labs.types.SkiPassType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ameks on 29.09.2016.
 */
public class SkiPassRegistrator {
    List<SkiPass> blockedCards = new ArrayList();

    public SkiPass createNewSkiPass(SkiPassType type) {
        return SkiPassFactory.createSkiPass(type);
    }

    public void blockSkiPass(SkiPass skiPass) {
        blockedCards.add(skiPass);
    }

    public void unblockSkiPass(SkiPass skiPass) {
        blockedCards.remove(skiPass);
    }

    public boolean isSkiPassBlocked(SkiPass skiPass) {
        return blockedCards.contains(skiPass);
    }
}
