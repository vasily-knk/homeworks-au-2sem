package ru.spbau.kononenko.arraysum;

/**
 * Array splitter that creates additional summers
 * @author Vasily Kononenko
 * @version %I%, %G%
 */
public abstract class Splitter extends SummerBase {
    private SummerBase[] summers;
    private Thread[] threads;

    /**
     * Constructor
     * @param array the array to sum
     * @param min lower sum bound
     * @param max upper sum bound
     * @param numSummers number of summers to create
     */
    public Splitter(int[] array, int min, int max, int numSummers) {
        super(array, min, max);
        summers = new SummerBase[numSummers];
        threads = new Thread[numSummers];

        int offset = min;
        int stride = ((max - min) + numSummers - 1) / numSummers;

        for (int i = 0; i < numSummers; ++i, offset += stride) {
            int upperBound = Math.min(max, offset + stride);
            summers[i] = createSummer(offset, upperBound);
            threads[i] = new Thread(summers[i]);
        }
    }
    
    @Override
    public void run() {
        for (int i = 0; i < summers.length; ++i)
            threads[i].start();

        for (int i = 0; i < summers.length; ++i) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                return;
            }
            sum += summers[i].getSum();
        }
    }

    /**
     * Creates a new summer used by this splitter
     * @param min lower sum bound
     * @param max upper sum bound
     * @return the newly created summer
     */
    abstract protected SummerBase createSummer(int min, int max);
}
