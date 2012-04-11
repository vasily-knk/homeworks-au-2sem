package ru.spbau.kononenko.task2;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * PatternFilter is FileFilter implementation that accepts only files
 * NOT matching the regular expression.
 * @author Vasily Kononenko
 * @version %I%, %G%
 * @see FileFilter
*/
public class PatternFilter implements FileFilter {
    private final Pattern pattern;

    /**
     * Creates a new PatternFilter instance
     * @param regex regular expression to match
     */
    public PatternFilter(String regex) {
        pattern = Pattern.compile(regex);
    }

    @Override
    public boolean accept(File pathname) {
        Matcher m = pattern.matcher(pathname.getName());
        return !m.matches();
    }
}