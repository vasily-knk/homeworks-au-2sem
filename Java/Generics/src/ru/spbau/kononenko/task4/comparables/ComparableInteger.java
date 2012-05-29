package ru.spbau.kononenko.task4.comparables;

/**
 * Comparable Integer.
 * @author Vasily Kononenko
 * @version %I%, %G%
*/
public class ComparableInteger extends Keeper<Integer> implements MyComparable<ComparableInteger> {

    /**
     * Initializes this comparable integer.
     * @param value integer value
     */
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
