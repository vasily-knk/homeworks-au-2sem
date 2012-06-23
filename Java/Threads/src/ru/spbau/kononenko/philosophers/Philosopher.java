package ru.spbau.kononenko.philosophers;

import java.util.Random;

/**
 * Philosopher who can eat and sleep
 * @author Vasily Kononenko
 * @version %I%, %G%
*/
public class Philosopher implements Runnable{
    private int id;
    private final Fork left, right;
    private Random random = new Random();

    private static final int MAX_SLEEP_TIME = 500;
    private static final int MAX_EAT_TIME = 500;


    /**
     *
     * @param id philosopher id
     * @param left the fork to the left
     * @param right the fork to the right
     */
    public Philosopher(int id, Fork left, Fork right) {
        this.id = id;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            boolean leftTaken = takeFork(left);
            boolean rightTaken = leftTaken && takeFork(right);

            try {
                if (leftTaken && rightTaken)
                    eat();
            } catch (InterruptedException e) {
                break;
            } finally {
                if (rightTaken)
                    putFork(right);
                if (leftTaken)
                    putFork(left);
            }


            try {
                sleep();
            } catch (InterruptedException e) {
                break;
            }
        }
    }



    private void sleep() throws InterruptedException {
        System.out.println(getName() + " starts sleeping");
        Thread.sleep(random.nextInt(MAX_SLEEP_TIME));
        System.out.println(getName() + " finishes sleeping");
    }

    private void eat() throws InterruptedException {
        System.out.println(getName() + " starts eating");
        Thread.sleep(random.nextInt(MAX_EAT_TIME));
        System.out.println(getName() + " finishes eating");
    }

    private boolean takeFork(Fork fork) {
        boolean result;
        synchronized (fork) {
            result = fork.tryToTake();
        }

        System.out.println(getName() + (result ? " takes " : " fails to take ")
                + "the " + getForkName(fork) + " fork.");
        return result;

    }
    
    private void putFork(Fork fork) {
        synchronized (fork) {
            fork.put();
        }

        System.out.println(getName() + " puts the " + getForkName(fork) + " fork.");
    }
    
    private String getForkName(Fork fork) {
        return (fork == left ? "left" : "right"); 
    }
    
    private String getName() {
        return "Philosopher " + id;
    }
}
