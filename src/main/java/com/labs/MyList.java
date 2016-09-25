package com.labs;

public interface MyList {
    /**добавляет элемент в конец списка*/
    void add(Object e);
    /**добавляет элемент в указанное место*/
    void add(int index, Object element);
    /**добавляет массив элементов в конец списка*/
    void addAll(Object[] c);
    /**добавляет массив элементов в указанное место списка*/
    void addAll(int index, Object[] c);
    /**возвращает элемент по индексу*/
    Object get(int index);
    /**удаляет элемент по индексу*/
    Object remove(int index);
    /**изменяет значение элемента*/
    void set(int index, Object element);
    /**поиск индекса по значению элемента*/
    int indexOf(Object o);
    /**размер списка*/
    int size();
    /**преобразует список в массив объектов*/
    Object[] toArray();
}
