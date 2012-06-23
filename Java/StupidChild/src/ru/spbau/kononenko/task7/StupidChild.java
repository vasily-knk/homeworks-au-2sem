package ru.spbau.kononenko.task7;

import java.util.Random;

/**
 * Class which generates numbers and calls external incremetor
 * @author Vasily Kononenko
 * @version %I%, %G%
 */
public class StupidChild implements Runnable {
    private int id;
    private int[] array;
    private DistributedIncrementor incrementor;

    /**
     * Class constructor
     * @param id child id
     * @param arraySize total numbers to generate
     * @param min minimum number
     * @param max maximum number
     * @param incrementor external incrementor to use
     */
    public StupidChild(int id, int arraySize, int min, int max, DistributedIncrementor incrementor) {
        this.id = id;
        array = new int[arraySize];

        Random random = new Random();
        for (int i = 0; i < arraySize; ++i)
            array[i] = min + random.nextInt(max - min);

        this.incrementor = incrementor;
    }

    @Override
    public void run() {
        for (int i : array) {
            int result;
            try {
                Thread.sleep(10);
                result = incrementor.increment(i);
            } catch (InterruptedException e) {
                break;
            }

            System.out.println("" + id + ": " + i + " changed to " + result);
        }
    }
}
