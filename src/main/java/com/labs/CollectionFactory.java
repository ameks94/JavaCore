package com.labs;

import com.labs.implementations.MyArrayList;
import com.labs.implementations.MyLinkedList;

public class CollectionFactory {
    static enum MyListType {
        ARRAY_LIST,
        LINKED_LIST
    }
    public static MyList getList(MyListType type) {
        switch (type) {
            case ARRAY_LIST:
                return new MyArrayList();
            case LINKED_LIST:
                return new MyLinkedList();

        }
        return null;
    }
}
