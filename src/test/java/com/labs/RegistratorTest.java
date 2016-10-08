package com.labs;

import junit.framework.Assert;
import org.junit.*;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ameks on 04.10.2016.
 */
public class RegistratorTest extends Assert {
    private static final SkiPassRegistrator registrator = new SkiPassRegistrator();
    private static final List<SkiPass> skiPasses = new ArrayList<>();
    private static SkiPass existingSkiPass;
    private static SkiPass unexistingSkiPass;
    public static final LocalDate now = DateTimeHelper.nowDate();

    @BeforeClass
    public static void initialise() {
        SkiPass skiPass;
        for (int i = 0; i < 3; i++) {
            skiPass = SkiPass.createSeasonSkiPass(now);
            skiPasses.add(skiPass);
            registrator.registerSkiPass(skiPass);
        }
        existingSkiPass = skiPasses.get(0);

        unexistingSkiPass = SkiPass.createSeasonSkiPass(now);;
    }

    @Test
    public void testRegistratorBlocking() {
        registrator.blockSkiPass(existingSkiPass);
        assertTrue(registrator.isSkiPassBlocked(existingSkiPass));

        registrator.unblockSkiPass(existingSkiPass);
        assertFalse(registrator.isSkiPassBlocked(existingSkiPass));


    }

    @org.junit.Test
    public void testRegistratorUnblockException() {

        try {
            registrator.blockSkiPass(unexistingSkiPass);
            assertTrue(false);
        } catch (RuntimeException ex) {
            assertTrue(true);
        }

        try {
            registrator.unblockSkiPass(unexistingSkiPass);
            assertTrue(false);
        } catch (RuntimeException ex) {
            assertTrue(true);
        }
    }

    @org.junit.Test
    public void testRegistrator() {
        assertEquals(registrator.getRegisteredSkiPasses().size(), skiPasses.size());
        assertEquals(registrator.getBlockedSkiPasses().size(), 0);
    }


}
