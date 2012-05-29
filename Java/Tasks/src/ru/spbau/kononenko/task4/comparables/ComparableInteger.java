package ru.spbau.kononenko.task4.comparables;

import ru.spbau.kononenko.task4.Keeper;

public class ComparableInteger extends Keeper<Integer> implements MyComparable<ComparableInteger> {

    public ComparableInteger(Integer value) {
        super(value);
    }

    @Override
    public int compareTo(ComparableInteger x) {
        if (this.value < x.value)
            return -1;
        if (this.value > x.value)
            return 1;
        return 0;
    }
}
