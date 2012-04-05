package ru.spbau.kononenko.drunkgame;

public interface Field/*<Coord>*/ {
    public FieldObject getObject(Coord coord);
    public void setObject(Coord coord, FieldObject object);
}
