package ru.spbau.kononenko.task4;

public class Keeper<T> {
    protected T value;
    
    public Keeper(T value) {
        this.value = value;
    }
    
    public T getValue() {
        return value;
    }
    
    public void setValue(T value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return value.toString();
    }
        
}
