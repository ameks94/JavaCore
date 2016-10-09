package com.labs;

public interface MyList<T> {
    /**добавляет элемент в конец списка*/
    void add(T e);
    /**добавляет элемент в указанное место*/
    void add(int index, T element);
    /**добавляет массив элементов в конец списка*/
    void addAll(T[] c);
    /**добавляет массив элементов в указанное место списка*/
    void addAll(int index, T[] c);
    /**возвращает элемент по индексу*/
    Object get(int index);
    /**удаляет элемент по индексу*/
    Object remove(int index);
    /**изменяет значение элемента*/
    void set(int index, T element);
    /**поиск индекса по значению элемента*/
    int indexOf(T o);
    /**размер списка*/
    int size();
    /**преобразует список в массив объектов*/
    T[] toArray();
}
