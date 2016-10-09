package com.labs;

import com.labs.implementations.MyArrayList;

import static org.junit.Assert.*;

public class ListTestHelper {
    private MyList list;
    private static final String firstEl = "Hello";
    private static final String secondEl = "World";
    private static final String thirdEl = "It's my text";

    private static final int indexToAdd = 1;
    private static final String newFirstEl = "Hello new";
    private static final String newSecondEl = "World new";

    public void initializeList(CollectionFactory.MyListType type) {
        list = CollectionFactory.getList(type);
        list.add(ListTestHelper.firstEl);
        list.add(ListTestHelper.secondEl);
        list.add(ListTestHelper.thirdEl);
        list.add(null);
    }

    public void testArraySize() {
        assertEquals( list.size(), 4 );
    }

    public void testArrayIndexOf() {
        assertEquals( list.indexOf(firstEl), 0 );
        assertEquals( list.indexOf(secondEl), 1 );
        assertEquals( list.indexOf(thirdEl), 2 );
    }


    public void testArrayAddEll() {
        list.add(newFirstEl);
        assertEquals( list.indexOf(newFirstEl), list.size() - 1 );
        assertEquals( list.indexOf("Nonexistent element"), -1 );
    }


    public void testArrayAddEllInd() {
        list.add(indexToAdd, newFirstEl);
        assertEquals( list.indexOf(newFirstEl), indexToAdd );
    }


    public void testArrayAddAllEll() {
        Object[] elementsToAdd = MyArrayList.buildArray(newFirstEl, newSecondEl);
        int oldListSize = list.size();
        int newListSize = oldListSize + elementsToAdd.length;
        list.addAll(elementsToAdd);
        assertEquals( list.size(), newListSize );
        for (int i = 0; i < elementsToAdd.length; i++) {
            assertEquals( list.indexOf(elementsToAdd[i]), oldListSize + i );
        }
    }


    public void testArrayAddAllEllInd() {
        Object[] elementsToAdd = MyArrayList.buildArray(newFirstEl, newSecondEl);
        int oldListSize = list.size();
        int newListSize = oldListSize + elementsToAdd.length;
        list.addAll(indexToAdd, elementsToAdd);
        assertEquals( list.size(), newListSize );
        for (int i = 0; i < elementsToAdd.length; i++) {
            assertEquals( list.indexOf(elementsToAdd[i]), indexToAdd + i );
        }
    }


    public void testRemove() {
        int indexToRemove = indexToAdd;
        int oldListSize = list.size();
        Object shouldBeRemoved = list.get(indexToRemove);
        Object removedObj = list.remove(indexToRemove);
        assertEquals( shouldBeRemoved, removedObj );
        assertEquals( list.size(), oldListSize - 1 );
    }


    public void testToArray() {
        Object[] array = list.toArray();
        assertNotNull(array);
        assertArrayEquals(array, new Object[]{ firstEl, secondEl, thirdEl, null });
    }


    public void testSet() {
        int oldSize = list.size();
        list.set(indexToAdd, newFirstEl);
        assertEquals(list.get(indexToAdd), newFirstEl);
        assertEquals(list.size(), oldSize);
    }

    public void testGet() {
        int indexToGet = 1;
        assertEquals(list.get(indexToGet), secondEl);
    }

    public void testIndexOfNull() {
        int indexToGet = 3;
        assertEquals(list.get(indexToGet), null);
    }
}
