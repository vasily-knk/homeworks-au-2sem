package ru.spbau.kononenko.task4.sorters;

import ru.spbau.kononenko.task4.comparables.MyComparable;
import ru.spbau.kononenko.task4.comparators.DefaultComparator;

import java.util.List;

/**
 * The sorter abstract class for comparable objects.
 * @author Vasily Kononenko
 * @version %I%, %G%
*/
abstract class ComparableSorter implements Sorter {
    @Override
    public <T extends MyComparable<? super T>> void sort(List<T> list) {
        sort(list, new DefaultComparator<T>());
    }
}
