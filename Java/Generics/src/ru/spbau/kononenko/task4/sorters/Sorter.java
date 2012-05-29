package ru.spbau.kononenko.task4.sorters;

import ru.spbau.kononenko.task4.comparables.MyComparable;
import ru.spbau.kononenko.task4.comparators.MyComparator;

import java.util.List;

/**
 * The sorter interface.
 * @author Vasily Kononenko
 * @version %I%, %G%
*/
public interface Sorter {
    /**
     * Sorts the specified list of comparable objects.
     * @param list the list to be sorted
     * @param <T> the comparable type
     */
    <T extends MyComparable<? super T>> void sort(List<T> list);

    /**
     * Sorts the specified list using the specified comparator.
     * @param list the list to be sorted
     * @param comparator comparator to use
     * @param <T> the generic type
     */
    <T> void sort(List<T> list, MyComparator<? super T> comparator);
}
