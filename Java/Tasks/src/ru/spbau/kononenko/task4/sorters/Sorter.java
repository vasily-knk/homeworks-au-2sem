package ru.spbau.kononenko.task4.sorters;

import ru.spbau.kononenko.task4.comparables.MyComparable;
import ru.spbau.kononenko.task4.comparators.MyComparator;

import java.util.List;

public interface Sorter {
    public <T extends MyComparable<? super T>> void sort(List<T> list);
    public <T> void sort(List<T> list, MyComparator<? super T> comparator);
}
