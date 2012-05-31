package ru.spbau.kononenko.task6.property;

/**
 * The property interface used to manipulate object's properties.
 * @author Vasily Kononenko
 * @version %I%, %G%
*/
public interface Property {
    /**
     * Gets the property value.
     * @return string representing the value
     */
    String get();

    /**
     * Gets the property value.
     * @param value string representing the value
     * @throws PropertyReadOnlyException if the property is read-only
     */
    void set(String value) throws PropertyReadOnlyException;

    /**
     * Checks if the property is read-only
     * @return if the property is read-only
     */
    boolean isReadonly();

    /**
     * Gets the property name.
     * @return the property nam.
     */
    String getName();

    /**
     * Check if the specified type is supported by these properties.
     * @param type the desired type
     * @return true if the type is supported, else otherwise
     */
    public boolean isSupportedType(Class<?> type);
}
