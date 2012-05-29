package ru.spbau.kononenko.task4.comparators;

import ru.spbau.kononenko.task4.comparables.ComparableInteger;

public class IntegerModComparator implements MyComparator<ComparableInteger> {
    private int n;

    public IntegerModComparator(int n) {
        if (n == 0)
            throw new ZeroModException("Can't create IntegerModComparator with n = 0");
        this.n = n;
    }

    @Override
    public int compare(ComparableInteger x, ComparableInteger y) {
        final ComparableInteger x1 = new ComparableInteger(x.getValue() % n);
        final ComparableInteger y1 = new ComparableInteger(y.getValue() % n);
        return x1.compareTo(y1);
    }
}
