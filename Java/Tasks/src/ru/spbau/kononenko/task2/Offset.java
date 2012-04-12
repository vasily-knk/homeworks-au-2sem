package ru.spbau.kononenko.task2;

class Offset {
    private final String string;

    public Offset(int length, boolean hanging) {
        StringBuilder builder = new StringBuilder(length);
        builder.append(hanging ? "|" : " ");
        for (int i = 0; i < length - 1; ++i)
            builder.append(" ");
        string = builder.toString();
    }

    public String toString() {
        return string;
    }
}