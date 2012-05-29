package ru.spbau.kononenko.task4.sorters;

import ru.spbau.kononenko.task4.comparators.MyComparator;
import ru.spbau.kononenko.task4.comparables.MyComparable;

import java.util.Collections;
import java.util.List;

public class DummySorter extends AbstractSorter {

    @Override
    public <T> void sort(List<T> list, MyComparator<? super T> comparator) {
        if (list.size() >= 2)
        {
            if (comparator.compare(list.get(0), list.get(1)) < 0)
                Collections.swap(list, 0, 1);
        }
    }
}
