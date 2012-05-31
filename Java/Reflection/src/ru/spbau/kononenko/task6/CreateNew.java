package ru.spbau.kononenko.task6;

import ru.spbau.kononenko.task6.manager.GetSetPropertiesManager;
import ru.spbau.kononenko.task6.manager.PropertiesManager;
import ru.spbau.kononenko.task6.serialization.ReflectionSerializer;
import ru.spbau.kononenko.task6.serialization.ReflectionSerializerInterface;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Alternative main class used to create a new record.
 * @author Vasily Kononenko
 * @version %I%, %G%
 */
public class CreateNew {
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

        Student student = new Student();
        student.setName("John");
        student.setSurname("Smith");
        student.setAge(19);
        student.setAvgGrade(3.1415926);
        
        PropertiesManager manager = new GetSetPropertiesManager();
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
