package ru.spbau.kononenko.task4.generators;

import ru.spbau.kononenko.task4.comparables.ComparableString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Random ComparableString generator.
 * Generate strings up to maxLength characters long.
 */
public class RandomStringGenerator implements Generator<ComparableString> {
    private final Random random = new Random();
    private final int maxLength;

    /**
     *
     * @param maxLength max string length
     */
    public RandomStringGenerator(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public List<ComparableString> generate(int count) {
        List<ComparableString> list = new ArrayList<ComparableString>(count);
        for (int i = 0; i < count; ++i)
            list.add(new ComparableString(makeString()));
        return list;
    }
    
    private String makeString() {
        int length = random.nextInt(maxLength);
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; ++i)
            builder.append((char)('a' + random.nextInt(24)));
        return builder.toString();
    }
}
