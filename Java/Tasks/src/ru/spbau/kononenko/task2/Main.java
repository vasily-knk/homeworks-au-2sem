package ru.spbau.kononenko.task2;

/**
 * Main class
 * @author Vasily Kononenko
 * @version %I%, %G%
 */
public class Main {
    /**
     * program entry point
     * @param args command line args
     */
    public static void main(String[] args) {
        FilesystemWalker.walkDir((args.length != 0) ? args[1] : ".", new PatternFilter("\\.*"));
    }
}
