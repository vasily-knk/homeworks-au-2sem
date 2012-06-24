package ru.spbau.kononenko.arraysum;

import java.util.Random;

/**
 * The main class
 * @author Vasily Kononenko
 * @version %I%, %G%
*/
public class Main {
    private final static int ARRAY_SIZE = 1000;
    private final static int NUM_SPLITTERS = 4;
    private final static int NUM_SUBSPLITTERS = 3;

    
    /**
     * Program entry point
     * @param args command-line args
     */
    public static void main(String[] args) {
        Random random = new Random();

        int[] arr = new int[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; ++i)
            arr[i] = random.nextInt(100);

        
        SummerBase summer = new Splitter(arr, 0, ARRAY_SIZE, NUM_SPLITTERS) {
            @Override
            protected SummerBase createSummer(int min, int max) {
                return new Splitter(array, min, max, NUM_SUBSPLITTERS) {
                    @Override
                    protected SummerBase createSummer(int min, int max) {
                        return new RealSummer(array, min, max);
                    }
                };
            }
        };

        summer.run();
        System.out.println("Total sum: " + summer.getSum());
    }
}
