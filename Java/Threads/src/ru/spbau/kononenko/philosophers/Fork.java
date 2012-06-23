package ru.spbau.kononenko.philosophers;

/**
 * Fork: the resource philosophers share
 * @author Vasily Kononenko
 * @version %I%, %G%
*/
public class Fork {
    private boolean taken = false;

    /**
     * Tries to take this fork
     * @return true if this fork is not taken, false otherwise
     */
    public boolean tryToTake() {
        if (taken)
            return false;
        taken = true;
        return true;
    }

    /**
     * Puts this fork
     */
    public void put() {
        taken = false;
    }

    /**
     *
     * @return true if this fork is taken, false otherwise
     */
    public boolean isTaken() {
        return taken;
    }
}
