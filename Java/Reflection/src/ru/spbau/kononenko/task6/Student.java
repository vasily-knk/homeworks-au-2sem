package ru.spbau.kononenko.task6;

import java.util.Random;

/**
 * The class to (de)serialize.
 *
 */
public class Student {
    private String name = "N/A";
    private String surname = "N/A";
    private int age = 0;
    private double avgGrade = 0;
    private int secret = new Random().nextInt(100);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(double avgGrade) {
        this.avgGrade = avgGrade;
    }

    public int getSecret() {
        return secret;
    }

}
