package ru.spbau.kononenko.task4.generators;

import ru.spbau.kononenko.task4.comparables.ComparableInteger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Random ComparableInteger generator.
 * Generates values in [0..max) range.
 */
public class RandomIntGenerator implements Generator<ComparableInteger> {
    private final Random random = new Random();
    private final int max;

    /**
     * Initializes this generator.
     * @param max the range size
     */
    public RandomIntGenerator(int max) {
        this.max = max;
    }

    @Override
    public List<ComparableInteger> generate(int count) {
        List<ComparableInteger> list = new ArrayList<ComparableInteger>(count);
        for (int i = 0; i < count; ++i)
            list.add(new ComparableInteger(random.nextInt(max)));
        return list;
    }
}
