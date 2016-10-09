package com.labs.implementations;

import com.labs.MyList;

import java.util.Arrays;
import java.util.RandomAccess;

public class MyArrayList<T> implements MyList<T>, RandomAccess {

    //---------------------------------Private fields-----------------------------//

    /**real array size*/
    private int size = 0;

    private int DEFAULT_CAPACITY = 10;

    private Object[] entries = new Object[DEFAULT_CAPACITY];

    //---------------------------------MyList api-----------------------------//
    @Override
    public void add(T e) {
        ensureCapacity(size + 1);
        entries[size++] = e;
    }

    @Override
    public void add(int index, T element) {
        checkIndexForAdd(index);
        ensureCapacity(size + 1);
        System.arraycopy(entries, index, entries, index + 1, size - index);
        entries[index] = element;
        size++;
    }

    @Override
    public void addAll(T[] c) {
        addAll(size, c);
    }

    @Override
    public void addAll(int index, T[] c) {
        checkIndexForAdd(index);
        int lengthToBeAdded = c.length;
        ensureCapacity(size + lengthToBeAdded);
        int elToBeMoved = size - index - 1;
        if (elToBeMoved > 0)
            System.arraycopy(entries, index, entries, index + lengthToBeAdded, elToBeMoved);
        System.arraycopy(c, 0, entries, index, lengthToBeAdded);
        size += lengthToBeAdded;
    }

    @Override
    public Object get(int index) {
        checkIndex(index);
        return entries[index];
    }

    @Override
    public Object remove(int index) {
        checkIndex(index);
        Object removedObj = entries[index];
        int elToBeMoved = size - index - 1;
        if (elToBeMoved > 0)
            System.arraycopy(entries, index + 1, entries, index, elToBeMoved);
        size--;
        return removedObj;
    }

    @Override
    public void set(int index, T element) {
        checkIndex(index);
        entries[index] = element;
    }

    @Override
    public int indexOf(T o) {
        if (o == null) {
            for(int i = 0; i < size; i++) {
                if ( entries[i] == null ) {
                    return i;
                }
            }
        } else {
            for(int i = 0; i < size; i++) {
                if ( o.equals(entries[i]) )
                    return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T[] toArray() {
        return (T[]) Arrays.copyOf(entries, size);
    }

    //---------------------------------Private methods and classes-----------------------------//

    private void checkIndex(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void checkIndexForAdd(int index) throws ArrayIndexOutOfBoundsException {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void ensureCapacity(int neededCapacity) {
        if (neededCapacity <= entries.length)
            return;
        int newCapacity = (int) ( size * 3. / 2 ) + 1;
        if ( newCapacity < neededCapacity) {
            newCapacity = neededCapacity;
        }
        Object[] newEntries = new Object[newCapacity];
        System.arraycopy(newEntries, 0, entries, 0, size);
        entries = newEntries;
    }

    /**Convenient way to initialize Object[]*/
    public static Object[] buildArray(Object ...arr) {
        return arr;
    }

}
