package ru.spbau.kononenko.philosophers;

/**
 * The main class
 * @author Vasily Kononenko
 * @version %I%, %G%
*/
public class Main {
    static final int NUM_FORKS = 5;

    /**
     * Program entry point
     * @param args command-line args
     */
    public static void main(String[] args) {
        Fork[] forks = new Fork[NUM_FORKS];
        for (int i = 0; i < NUM_FORKS; ++i) {
            forks[i] = new Fork();
        }

        Philosopher[] philosophers = new Philosopher[NUM_FORKS];
        for (int i = 0; i < NUM_FORKS; ++i) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % NUM_FORKS]);
            new Thread(philosophers[i]).start();
        }
    }
}
