package ru.spbau.kononenko.task4.sorters;

import ru.spbau.kononenko.task4.comparators.MyComparator;

import java.util.Collections;
import java.util.List;

public class HeapSort extends AbstractSorter {

    private static class Sorting<T> {
        List<T> list;
        MyComparator<? super T> comparator;

        public Sorting(List<T> list, MyComparator<? super T> comparator) {
            this.list = list;
            this.comparator = comparator;
        }

        public void sort() {
            buildHeap();
            int size = list.size();
            for (int i = 0; i < list.size() - 1; ++i) {
                Collections.swap(list, 0, --size);
                heapify(0, size);
            }
        }

        private void buildHeap() {
            for(int i = list.size() / 2; i > -1; --i)
                heapify(i, list.size());
        }

        private void heapify(int i, int size) {
            int maxIndex = i;
            int left;
            int right;

            if(i == 0) {
                left  = 1;
                right = 2;
            } else {
                left  = 2 * i;
                right = 2 * i + 1;
            }

            maxIndex = getMax(size, maxIndex, left);
            maxIndex = getMax(size, maxIndex, right);

            if (i != maxIndex) {
                Collections.swap(list, i, maxIndex);
                heapify(maxIndex, size);
            }
        }

        private int getMax(int size, int maxIndex, int child) {
            if (child < size) {
                if(comparator.compare(list.get(child), list.get(maxIndex)) > 0)
                    maxIndex = child;
            }
            return maxIndex;
        }
    }

    @Override
    public <T> void sort(List<T> list, MyComparator<? super T> comparator) {
        Sorting s = new Sorting(list, comparator);
        s.sort();
    }
}
