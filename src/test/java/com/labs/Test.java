package com.labs;

import org.junit.Assert;

import java.time.LocalTime;

public class Test extends Assert {
    @org.junit.Test
    public void test() {
        LocalTime time = LocalTime.parse("09:00");
        System.out.println(time);
    }

}
