package ru.spbau.kononenko.drunkgame.Field;

public interface Field extends FieldGeometry {
    public FieldObject getObject(Coord coord);
    public void setObject(Coord coord, FieldObject object);
    public void removeObject(Coord coord);

    public void draw();
}
