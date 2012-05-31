package ru.spbau.kononenko.task6;

/**
 * The excpetion to be thrown when a property isn't found.
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
