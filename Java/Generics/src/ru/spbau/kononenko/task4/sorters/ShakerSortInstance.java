package ru.spbau.kononenko.task4.sorters;

import ru.spbau.kononenko.task4.comparators.MyComparator;

import java.util.Collections;
import java.util.List;

class ShakerSortInstance<T> extends SortInstance<T> {

    protected ShakerSortInstance(List<T> list, MyComparator<? super T> comparator) {
        super(list, comparator);
    }

    @Override
    public void sort() {
        int j;
        int end = list.size();
        int start = -1;
        while (start < end) {
            boolean swapped = false;
            ++start;
            --end;

            for (j = start; j < end; ++j) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    Collections.swap(list, j, j + 1);
                    swapped = true;
                }
            }

            if (!swapped) {
                return;
            }

            swapped = false;
            for (j = end; --j >= start;) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    Collections.swap(list, j, j + 1);
                    swapped = true;
                }
            }

            if (!swapped) {
                return;
            }
        }
    }
}
