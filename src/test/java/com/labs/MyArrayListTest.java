package com.labs;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyArrayListTest extends Assert {
    ListTestHelper testHelper = new ListTestHelper();

    @Before
    public void initializeArray() {
        testHelper.initializeList(CollectionFactory.MyListType.ARRAY_LIST);
    }

    @Test
    public void testArraySize() {
        testHelper.testArraySize();
    }

    @Test
    public void testArrayIndexOf() {
        testHelper.testArrayIndexOf();
    }

    @Test
    public void testArrayAddEll() {
        testHelper.testArrayAddEll();
    }

    @Test
    public void testArrayAddEllInd() {
        testHelper.testArrayAddEllInd();
    }

    @Test
    public void testArrayAddAllEll() {
        testHelper.testArrayAddAllEll();
    }

    @Test
    public void testArrayAddAllEllInd() {
        testHelper.testArrayAddAllEllInd();
    }

    @Test
    public void testRemove() {
        testHelper.testRemove();
    }

    @Test
    public void testToArray() {
        testHelper.testToArray();
    }

    @Test
    public void testSet() {
        testHelper.testSet();
    }

    @Test
    public void testGet() {
        testHelper.testGet();
    }

    @Test
    public void testIndexOfNull() {
        testHelper.testIndexOfNull();
    }
}
