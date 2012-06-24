package ru.spbau.kononenko.arraysum;

/**
 * Array summer
 * @author Vasily Kononenko
 * @version %I%, %G%
*/
public abstract class SummerBase implements Runnable {
    /**
     * The array to sum
     */
    protected final int[] array;

    /**
     * Sum bounds
     */
    protected final int min, max;

    /**
     * Sum result
     */
    protected int sum = 0;

    /**
     * Constructor
     * @param array the array to sum
     * @param min lower sum bound
     * @param max upper sum bound
     */
    public SummerBase(int[] array, int min, int max) {
        this.array = array;
        this.min = min;
        this.max = max;
    }

    /**
     * Sum result getter
     * @return sum result
     */
    public int getSum() {
        return sum;
    }
}
