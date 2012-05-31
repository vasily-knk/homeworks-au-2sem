package ru.spbau.kononenko.task6.manager;

import ru.spbau.kononenko.task6.property.PropertyNotFoundException;
import ru.spbau.kononenko.task6.property.UnsupportedPropertyTypeException;
import ru.spbau.kononenko.task6.property.Property;

import java.util.List;

/**
 * This interface is used to manipulate class properties.
 * @author Vasily Kononenko
 * @version %I%, %G%
*/
public interface PropertiesManager {
    /**
     * Gets property with the name specified.
     * @param t the object whose property is required
     * @param name the property name
     * @param <T> the object type
     * @return the Property object representing the manipulated property
     * @throws UnsupportedPropertyTypeException if the property type is not supported
     * (not primitive type, wrapper or string)
     * @throws PropertyNotFoundException if the property doesn't exist
     */
    <T> Property getProperty(T t, String name) throws UnsupportedPropertyTypeException,
            PropertyNotFoundException;

    /**
     * Gets all object's properties.
     * @param t the object properties is required
     * @param <T> the object type
     * @return the list of all object's properties
     */
    <T> List<Property> getAllProperties(T t);
}
