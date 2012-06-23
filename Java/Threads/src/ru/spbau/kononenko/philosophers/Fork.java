package ru.spbau.kononenko.philosophers;

public class Fork {
    private boolean taken = false;

    public boolean tryToTake() {
        if (taken)
            return false;
        taken = true;
        return true;
    }

    public void put() {
        taken = false;
    }
    
    public boolean isTaken() {
        return taken;
    }
}
