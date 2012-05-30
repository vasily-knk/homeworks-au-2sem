package ru.spbau.kononenko.drunkgame.Field;

public interface FieldObject {
    public boolean getProperty(FieldObjectProperty property);
    public char getChar();
    void onRemove(Coord coord);
    void onPlace(Coord coord);
}
