package ru.spbau.kononenko.task4.sorters;

import ru.spbau.kononenko.task4.comparators.MyComparator;

import java.util.List;

abstract class SortInstance<T> {
    protected final List<T> list;
    protected final MyComparator<? super T> comparator;

    protected SortInstance(List<T> list, MyComparator<? super T> comparator) {
        this.comparator = comparator;
        this.list = list;
    }

    abstract public void sort();
}
