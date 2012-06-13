package ru.spbau.kononenko.drunkgame.common.field.field_itself;

import ru.spbau.kononenko.drunkgame.common.field.objects.FieldObject;

public abstract class Field implements FieldGeometry {
    public abstract FieldObject getObject(Coord coord);

    public void setObject(Coord coord, FieldObject object) {
        if (getObject(coord) != null)
            throw new FieldOccupiedException(coord.toString());
        object.onPlace(coord);
    }

    public void removeObject(Coord coord) {
        FieldObject object = getObject(coord);
        if (object == null)
            throw new FieldFreeException(coord.toString());
        object.onRemove(coord);
    }

    public void moveObject(Coord src, Coord dst) {
        if (src.equals(dst))
            return;

        FieldObject o = getObject(src);
        setObject(dst, o);
        removeObject(src);
    }

    public abstract void draw();
}
