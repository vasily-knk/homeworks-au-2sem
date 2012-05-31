package ru.spbau.kononenko.task6.property;

/**
 * The excpetion to be thrown when a property isn't found.
 * @author Vasily Kononenko
 * @version %I%, %G%
*/
public class PropertyNotFoundException extends Exception {
    /**
     *
     * @param clazz the object class
     * @param propertyName the property name
     */
    public PropertyNotFoundException(Class clazz, String propertyName) {
        super("Property " + propertyName + " not found in class " + clazz.getName());
    }
}
