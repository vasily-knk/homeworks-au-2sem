package ru.spbau.kononenko.task4.sorters;

import ru.spbau.kononenko.task4.comparables.MyComparable;
import ru.spbau.kononenko.task4.comparators.DefaultComparator;

import java.util.List;

public abstract class AbstractSorter implements Sorter {
    @Override
    public <T extends MyComparable<? super T>> void sort(List<T> list) {
        sort(list, new DefaultComparator<T>());
    }
}
