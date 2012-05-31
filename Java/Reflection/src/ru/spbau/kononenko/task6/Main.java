package ru.spbau.kononenko.task6;


import java.util.List;

/**
 * The main class.
 * @author Vasily Kononenko
 * @version %I%, %G%
 */

public class Main {
    /**
     * Program entry point
     * @param args command line args
     */
    public static void main(String[] args) {
        Student student = new Student();
        PropertiesManager manager = new GetSetPropertiesManager();
        List<Property> list = manager.getAllProperties(student);
        
        for (Property property : list) {
            System.out.println(property.getName() + ": " + property.isReadonly());
        }
    }
}
