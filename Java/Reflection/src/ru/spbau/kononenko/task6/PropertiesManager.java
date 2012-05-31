package ru.spbau.kononenko.task6;

import java.util.List;

public interface PropertiesManager {
    <T> Property getProperty(T t, String name) throws UnsupportedPropertyTypeException, PropertyNotFoundException;
    <T> List<Property> getAllProperties(T t);
}
