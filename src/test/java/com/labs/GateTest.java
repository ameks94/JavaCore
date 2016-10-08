package com.labs;

import org.junit.Test;

import static com.labs.SkipassTestHelper.expiredDateTuesday;
import static org.junit.Assert.assertEquals;

public class GateTest {
    private static final SkiPassRegistrator registrator = new SkiPassRegistrator();
    private static final PayGate gate = new PayGate(registrator);
    
    
    @Test
    public void testCheckAndUseSkiPass() {
        SkiPass seasonSkiPass = SkiPass.createSeasonSkiPass(expiredDateTuesday);
        registrator.registerSkiPass(seasonSkiPass);
        gate.checkAndUseCard(seasonSkiPass);
    }

    @Test
    public void testGetRegisteredSkiPasses() {
        SkiPass seasonSkiPass = SkiPass.createSeasonSkiPass(expiredDateTuesday);
        registrator.registerSkiPass(seasonSkiPass);
        assertEquals(gate.getRegisteredSkiPasses().size(), 1);
    }
    
}
