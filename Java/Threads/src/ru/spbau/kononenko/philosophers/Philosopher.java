package ru.spbau.kononenko.philosophers;

public class Philosopher implements Runnable{
    private int id;
    private final Fork left, right;
    
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
            
            if (leftTaken && rightTaken)
                eat();
            else
                sleep();
            
            if (rightTaken)
                putFork(right);
            if (leftTaken)
                putFork(left);
            
        }
    }

    private void sleep() {
        System.out.println(getName() + " sleeps");
    }

    private void eat() {
        System.out.println(getName() + " eats");
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

        System.out.println(getName() + "puts the " + getForkName(fork) + " fork.");
    }
    
    private String getForkName(Fork fork) {
        return (fork == left ? "left" : "right"); 
    }
    
    private String getName() {
        return "Philosopher " + id;
    }
}
