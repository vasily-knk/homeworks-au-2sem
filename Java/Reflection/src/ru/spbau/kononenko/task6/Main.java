package ru.spbau.kononenko.task6;


import ru.spbau.kononenko.task6.manager.GetSetPropertiesManager;
import ru.spbau.kononenko.task6.manager.PropertiesManager;
import ru.spbau.kononenko.task6.property.PropertyNotFoundException;
import ru.spbau.kononenko.task6.property.UnsupportedPropertyTypeException;
import ru.spbau.kononenko.task6.serialization.ReflectionDeSerializer;
import ru.spbau.kononenko.task6.serialization.ReflectionDeSerializerInterface;
import ru.spbau.kononenko.task6.serialization.ReflectionSerializer;
import ru.spbau.kononenko.task6.serialization.ReflectionSerializerInterface;

import java.io.FileNotFoundException;
import java.io.IOException;

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
        if (args.length == 0) {
            System.out.println("Usage: program filename");
            return;
        }
        String filename = args[0];

        PropertiesManager manager = new GetSetPropertiesManager();
        ReflectionDeSerializerInterface src = new ReflectionDeSerializer(manager);

        Student student = null;
        try {
            student = src.deserialize(filename, Student.class);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedPropertyTypeException e) {
            System.out.println(e.getMessage());
        } catch (PropertyNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        student.setAvgGrade(Math.min(student.getAvgGrade() + 1, 5.0));

        ReflectionSerializerInterface dst = new ReflectionSerializer(manager);
        try {
            dst.serialize(student, filename);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
