package ru.spbau.kononenko.task4.comparables;

/**
 * Class for objects keeping its value inside (like ComparableInteger).
 * @param <T>
 * @author Vasily Kononenko
 * @version %I%, %G%
*/
public class Keeper<T> {
    /**
     * the value kept.
     */
    protected T value;

    /**
     * Initializes this object.
     * @param value value to initialize with
     */
    public Keeper(T value) {
        this.value = value;
    }

    /**
     * Returns the kept value.
     * @return the kept value
     */
    public T getValue() {
        return value;
    }

    /**
     * Changes the kept value.
     * @param value value to set
     */
    public void setValue(T value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return value.toString();
    }
        
}
