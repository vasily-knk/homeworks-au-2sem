package ru.spbau.kononenko.task6;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class GetSetProperty implements Property {
    private Object obj;
    private String name;
    private Method getter = null;
    private Method setter = null;
    private Method valueOf;

    public GetSetProperty(Object obj, String name) throws PropertyNotFoundException, UnsupportedPropertyTypeException {
        this.obj = obj;
        if (name.length() == 0)
            throw new PropertyNotFoundException(obj.getClass(), name);

        this.name = name;

        String newName = name.substring(0, 1).toUpperCase() + name.substring(1);
        try {
            getter = obj.getClass().getMethod("get" + newName);
        } catch (NoSuchMethodException e) {
            throw new PropertyNotFoundException(obj.getClass(), name);           
        }

        Class<?> propertyType = getter.getReturnType();
        
        valueOf = getValueOfMethod(propertyType);
        if (valueOf == null)
            throw new UnsupportedPropertyTypeException(propertyType);

        try {
            setter = obj.getClass().getMethod("set" + newName, propertyType);
        } catch (NoSuchMethodException e) {
            // setter = null, ok
        }
    }

    
    @Override
    public String get() {
        Object arg;
        try {
            arg = getter.invoke(obj);
        } catch (IllegalAccessException e) {
            throw new UnexpectedException(e);
        } catch (InvocationTargetException e) {
            throw new UnexpectedException(e);
        }
        return arg.toString();
    }

    @Override
    public void set(String value) {
        try {
            Object arg = valueOf.invoke(null, value);
            setter.invoke(obj, arg);
        } catch (IllegalAccessException e) {
            throw new UnexpectedException(e);
        } catch (InvocationTargetException e) {
            throw new UnexpectedException(e);
        }
    }

    @Override
    public boolean isReadonly() {
        return setter == null;
    }

    @Override
    public String getName() {
        return name;
    }

    public static boolean isSupportedType(Class<?> type) {
        return getValueOfMethod(type) != null;
    }
    
    // Секция костылей и велосипедов
    
    private static Method getValueOfMethod(Class<?> type) {
        try {
            if (type == String.class)
                return GetSetProperty.class.getDeclaredMethod("stringIdle", String.class);
        
            Class<?> realClass = getNonPrimitive(type);
            return realClass.getMethod("valueOf", String.class);
        } catch (NoSuchMethodException e) {
            // Yeah, I hate using exceptions in logic myself
            return null;
        }
    }

    private static Class<?> getNonPrimitive(Class<?> clazz) {
        if (clazz == Integer.TYPE)
            return Integer.class;
        if (clazz == Float.TYPE)
            return Float.class;
        if (clazz == Double.TYPE)
            return Double.class;
        if (clazz == Boolean.TYPE)
            return Boolean.class;
        if (clazz == Character.TYPE)
            return Character.class;

        return clazz;
    }

    private static String stringIdle(String str) {
        return str;
    }

}
