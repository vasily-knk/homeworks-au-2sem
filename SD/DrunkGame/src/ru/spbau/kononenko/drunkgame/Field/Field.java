package ru.spbau.kononenko.drunkgame.Field;

public abstract class Field implements FieldGeometry {
    public abstract FieldObject getObject(Coord coord);
    public abstract void setObject(Coord coord, FieldObject object);
    public abstract void removeObject(Coord coord);

    public void moveObject(Coord src, Coord dst) {
        if (src.equals(dst))
            return;

        FieldObject o = getObject(src);
        setObject(dst, o);
        removeObject(src);
    }

    public abstract void draw();
}
