package ru.spbau.kononenko.drunkgame;

public interface Field<T> {
    public FieldObject getObject(T coord);
    public void setObject(T coord, FieldObject object);
    public void print();
}
