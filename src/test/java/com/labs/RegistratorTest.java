package com.labs;

import junit.framework.Assert;
import org.junit.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ameks on 04.10.2016.
 */
public class RegistratorTest extends Assert {
    private static final SkiPassRegistrator registrator = new SkiPassRegistrator();
    private static final List<SkiPass> skiPasses = new ArrayList<>();
    public static final LocalDate now = LocalDate.now();

    @BeforeClass
    public static void initialise() {
        SkiPass skiPass;
        for (int i = 0; i < 3; i++) {
            skiPass = SkiPass.createSeasonSkiPass(now);
            skiPasses.add(skiPass);
            registrator.registerSkiPass(skiPass);
        }
    }

    @org.junit.Test
    public void testRegistrator() {
        assertEquals(registrator.getRegisteredSkiPasses().size(), skiPasses.size());
        assertEquals(registrator.getBlockedSkiPasses().size(), 0);

        SkiPass forTest = skiPasses.get(0);
        registrator.blockSkiPass(forTest);
        assertTrue(registrator.isSkiPassBlocked(forTest));

        registrator.unblockSkiPass(forTest);
        assertFalse(registrator.isSkiPassBlocked(forTest));
    }

    @org.junit.Test
    public void testExceptionRegistrator() {
        SkiPass unexistingSkiPass = SkiPass.createSeasonSkiPass(now);
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
}
