package ru.spbau.kononenko.arraysum;

public class RealSummer extends SummerBase {
    public RealSummer(int[] array, int min, int max) {
        super(array, min, max);
    }

    @Override
    public void run() {
        sum = 0;
        for (int i = min; i < max; ++i)
            sum+=array[i];
    }

    public int getSum() {
        return sum;
    }
    
}
