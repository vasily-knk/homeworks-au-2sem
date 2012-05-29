package ru.spbau.kononenko.task4.sorters;

import ru.spbau.kononenko.task4.comparators.MyComparator;

import java.util.List;

/**
 * The shaker sort class.
 */
public class ShakerSort extends ComparableSorter {
    @Override
    public <T> void sort(List<T> list, MyComparator<? super T> comparator) {
        SortInstance<T> s = new ShakerSortInstance<T>(list, comparator);
        s.sort();
    }
}
