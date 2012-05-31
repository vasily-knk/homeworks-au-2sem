package ru.spbau.kononenko.task6.property;

/**
 * The excpetion to be thrown when trying to modify a read-only property
 * @author Vasily Kononenko
 * @version %I%, %G%
*/
public class PropertyReadOnlyException extends Exception {
    /**
     *
     * @param clazz the object class
     * @param propertyName the property name
     */
    public PropertyReadOnlyException(Class clazz, String propertyName) {
        super("Property " + clazz.getName() + "." + propertyName + " is read-only.");
    }
}
