package ru.spbau.kononenko.task4.sorters;

import ru.spbau.kononenko.task4.comparators.MyComparator;

import java.util.List;

/**
 * The heap sort class.
 * @author Vasily Kononenko
 * @version %I%, %G%
*/
public class HeapSort extends ComparableSorter {
    @Override
    public <T> void sort(List<T> list, MyComparator<? super T> comparator) {
        SortInstance<T> s = new HeapSortInstance<T>(list, comparator);
        s.sort();
    }
}
