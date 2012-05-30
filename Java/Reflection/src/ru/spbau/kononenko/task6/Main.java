package ru.spbau.kononenko.task6;

import java.lang.reflect.Field;

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
        ClassProperty<Student> property = new GetSetProperty<Student>(Student.class, "surname");
        Student student = new Student();
        student.setSurname("Pupkin");
        
        property.set(student, "Ivanov");
        
        String str = (String)property.get(student);
        System.out.println(str);

        int i = 4;
        DoSomething(4);
    }

    public static void DoSomething (Object i) {
        System.out.println(i.getClass().getSimpleName());
    }
}
