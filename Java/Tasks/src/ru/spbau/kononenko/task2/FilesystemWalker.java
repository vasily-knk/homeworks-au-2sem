package ru.spbau.kononenko.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class provides methods to walk through subdirectories of the specified path.
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
        walkDir(path, null);
    }

    /**
     * Walks through subdirectories of the specified path
     * displaying files and directories which match the filter.
     * @param path The path to walk through
     * @param filter The filter to apply
     */
    public static void walkDir(String path, PatternFilter filter) {
        File root = new File(path);
        root = root.getAbsoluteFile();
        walkRecursive(root, new ArrayList<Offset>(), true, filter);
    }

    private static void walkRecursive(File file, List<Offset> offsets, boolean isLast, PatternFilter filter) {
        for (Offset offset : offsets) {
            System.out.print(offset);
        }
        
        String title = (offsets.isEmpty() ? "" : "|_") + file.getName();
        System.out.print(title);

        if (!file.isDirectory()) {
            System.out.println();
            return;
        }

        // For some reason, if there is no read access to the directory
        // canRead() still returns TRUE while readFiles() returns NULL.
        // file.listFiles(filter) could be used instead of safeListChildren(file, filter),
        // but skipping canRead() check doesn't seem good to me.
        File[] children = safeListChildren(file, filter);

        if (children == null) {
            System.out.println(" (access denied)");
            return;
        } else
            System.out.println();

        Arrays.sort(children);

        offsets.add(new Offset(title.length(), !isLast));
        for (int i = 0; i < children.length; ++i)
            walkRecursive(children[i], offsets, (i == children.length - 1), filter);
        offsets.remove(offsets.size() - 1);
    }
    
    private static File[] safeListChildren(File parent, PatternFilter filter) {
        if (!parent.canRead())
            return null;
        
        File[] children;
        try {
            children = (filter == null) ? parent.listFiles() : parent.listFiles(filter);
        } catch (SecurityException e) {
            children = null;
        }
        return children;
    }
}
