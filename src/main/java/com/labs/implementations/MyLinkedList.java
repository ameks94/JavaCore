package com.labs.implementations;

import com.labs.MyList;

public class MyLinkedList implements MyList {

    //---------------------------------Private fields-----------------------------//

    private Node first;
    private Node last;
    private int size = 0;

    //---------------------------------MyList api-----------------------------//

    @Override
    public void add(Object o) {
        final Node oldLast = last;
        final Node newNode = new Node(oldLast, o, null);
        last = newNode;
        if (oldLast == null)
            first = newNode;
        else
            oldLast.next = newNode;
        size++;
    }

    @Override
    public void add(int index, Object o) {
        checkIndexForAdd(index);

        Node oldNodeAtIndex = findNode(index);

        final Node prevEl = oldNodeAtIndex.prev;
        final Node newNode = new Node(prevEl, o, oldNodeAtIndex);
        oldNodeAtIndex.prev = newNode;
        if (prevEl == null)
            first = newNode;
        else
            prevEl.next = newNode;
        size++;
    }

    @Override
    public void addAll(Object[] c) {
        addAll(size, c);
    }

    @Override
    public void addAll(int index, Object[] c) {
        checkIndexForAdd(index);

        Node oldNodeAtIndex;
        /**e-e-e-e(lastLeft)-newList....e(oldNodeAtIndex)-e-e*/
        Node lastLeft;
        if (index == size) {
            oldNodeAtIndex = null;
            lastLeft = last;
        } else {
            //we have to put new elements at index position
            //so, they will be before oldNodeAtIndex
            oldNodeAtIndex = findNode(index);
            lastLeft = oldNodeAtIndex.prev;
        }

        for (Object o : c) {
            Node newNode = new Node(lastLeft, o, null);
            if (lastLeft == null)
                first = newNode;
            else
                lastLeft.next = newNode;
            lastLeft = newNode;
        }

        //add rest right elements that were before or set lastLeft as a last element
        if (oldNodeAtIndex != null) {
            lastLeft.next = oldNodeAtIndex;
            oldNodeAtIndex.prev = lastLeft;
        } else {
            last = lastLeft;
        }

        size += c.length;
    }

    @Override
    public Object get(int index) {
        checkIndex(index);
        return findNode(index).value;
    }

    @Override
    public Object remove(int index) {
        checkIndex(index);
        Node removedNode = findNode(index);

        if (removedNode.prev == null) {
            first = removedNode.next;
        } else {
            removedNode.prev.next = removedNode.next;
            removedNode.prev = null;
        }

        if (removedNode.next == null) {
            last = removedNode.prev;
        } else {
            removedNode.next.prev = removedNode.prev;
            removedNode.next = null;
        }

        size--;
        return removedNode.value;
    }

    @Override
    public void set(int index, Object o) {
        checkIndex(index);
        Node foundNode = findNode(index);
        foundNode.value = o;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node node = first; node != null; node = node.next) {
                if (node.value == null)
                    return index;
                index++;
            }
        } else {
            for (Node node = first; node != null; node = node.next) {
                if (o.equals(node.value))
                    return index;
                index++;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node node = first;
        int i = 0;
        while(node != null) {
            array[i++] = node.value;
            node = node.next;
        }
        return array;
    }

    //---------------------------------Private methods and classes-----------------------------//

    private static class Node
    {
        /**prev == null - means that list is empty*/
        Node prev;
        Object value;
        /**next == null means that it's last element*/
        Node next;

        Node(Node prev, Object value, Node next)
        {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }

        static Node emptyNode() {
            return new Node(null, null, null);
        }
    }

    private Node findNode(int index) {
        if ( index < (size >> 1) ) {
            Node curNode = first;
            for (int i = 0; i < index; i++)
                curNode = curNode.next;
            return curNode;
        } else {
            //TODO CHECK with SIZE = 1 AND index = 0;
            Node curNode = last;
            for (int i = size - 1; i > index; i--)
                curNode = curNode.prev;
            return curNode;
        }
    }

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
}
