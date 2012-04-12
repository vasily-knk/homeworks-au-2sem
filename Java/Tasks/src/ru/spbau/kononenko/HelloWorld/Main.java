package ru.spbau.kononenko.HelloWorld;

import ru.spbau.kononenko.task2.FilesystemWalker;
import ru.spbau.kononenko.task2.PatternFilter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Pattern p = Pattern.compile("a*b");
        Matcher m = p.matcher("aaaaab");
        boolean b = m.matches();
    }
}
