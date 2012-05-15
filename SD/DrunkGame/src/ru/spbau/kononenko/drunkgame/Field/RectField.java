package ru.spbau.kononenko.drunkgame.Field;

import java.util.ArrayList;
import java.util.List;

public class RectField extends Field2D {
    public RectField(int width, int height) {
        super(width, height);
    }

    @Override
    public List<Coord> getAdjacent(Coord coord) {
        assertInside(coord);
        ArrayList<Coord> list = new ArrayList<Coord> ();

        Coord hor = new Coord(1, 0);
        Coord ver = new Coord(0, 1);

        if (coord.x > 0)
            list.add(coord.minus(hor));
        if (coord.y > 0)
            list.add(coord.minus(ver));
        if (coord.x < width - 1)
            list.add(coord.plus(hor));
        if (coord.y < height - 1)
            list.add(coord.plus(ver));

        return list;
    }

    @Override
    public void draw() {
        for (int j = 0; j < height; ++j) {
            StringBuilder builder = new StringBuilder(width);
            for (int i = 0; i < width; ++i)
            {
                FieldObject o = getObject(new Coord(i, j));
                builder.append(o == null ? '.' : o.getChar());
            }
            System.out.println(builder);
        }
    }
}