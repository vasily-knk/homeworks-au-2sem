package ru.spbau.kononenko.drunkgame.field.objects;

import ru.spbau.kononenko.drunkgame.field.field_itself.Coord;

public interface FieldObject {
    public boolean getProperty(FieldObjectProperty property);
    public char getChar();
    void onRemove(Coord coord);
    void onPlace(Coord coord);
}
