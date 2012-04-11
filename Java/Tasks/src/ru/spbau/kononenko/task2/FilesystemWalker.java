package ru.spbau.kononenko.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FilesystemWalker provides methods to walk through subdirectories of the specified path.
 * @author Vasily Kononenko
 * @version %I%, %G%
 */
public class FilesystemWalker {
    /**
     * Walks through subdirectories of the specified path
     * displaying files and directories.
     * @param path The path to walk through
     */
    public static void walkDir(String path) {
        walkRecursive(new File(path), new ArrayList<Integer>(), null);
    }

    /**
     * Walks through subdirectories of the specified path
     * displaying files and directories which match the filter.
     * @param path The path to walk through
     * @param filter The filter to apply
     */
    public static void walkDir(String path, PatternFilter filter) {
        walkRecursive(new File(path), new ArrayList<Integer>(), filter);
    }

    private static void walkRecursive(File file, List<Integer> offsets, PatternFilter filter) {
        for (int offset : offsets) {
            for (int i = 0; i < offset; ++i)
                System.out.print(" ");
            System.out.print("|");
        }
        
        String title = (offsets.isEmpty() ? "" : "_") + file.getName();
        System.out.print(title);

        if (!file.isDirectory()) {
            System.out.println();
            return;
        }

        File[] children = safeListChildren(file, filter);

        if (children == null) {
            System.out.println(" (access denied)");
            return;
        } else
            System.out.println();

        Arrays.sort(children);

        offsets.add(title.length());
        for (File child : children) {
            walkRecursive(child, offsets, filter);
        }
        offsets.remove(offsets.size() - 1);
    }
    
    private static File[] safeListChildren(File parent, PatternFilter filter) {
        if (!parent.canRead())
            return null;
        
        File[] children;
        try {
            // Weird thing: on Windows, if there is no read access to the directory
            // canRead() still returns true while readFiles() returns null.
            children = (filter == null) ? parent.listFiles() : parent.listFiles(filter);
        } catch (SecurityException e) {
            children = null;
        }
        return children;
    }
}
