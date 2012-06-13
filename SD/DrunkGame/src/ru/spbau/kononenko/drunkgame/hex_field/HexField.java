package ru.spbau.kononenko.drunkgame.hex_field;

import ru.spbau.kononenko.drunkgame.common.field.field_itself.Coord;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Field2D;
import ru.spbau.kononenko.drunkgame.common.field.objects.FieldObject;

import java.util.ArrayList;
import java.util.List;

public class HexField extends Field2D {
    public HexField(int width, int height) {
        super(width, height);
    }

    @Override
    public void draw() {
        System.out.println(this);
    }

    @Override
    public List<Coord> getAdjacent(Coord coord) {
        assertInside(coord);
        ArrayList<Coord> list = new ArrayList<Coord> ();

        Coord hor = new Coord(1, 0);
        Coord ver = new Coord(0, 1);
        Coord diag = new Coord(1, 1);

        if (coord.x > 0) {
            if (coord.y > 0)
                list.add(coord.minus(diag));
            list.add(coord.minus(hor));
        }
        if (coord.y > 0)
            list.add(coord.minus(ver));
        if (coord.x < width - 1) {
            if (coord.y < height - 1)
                list.add(coord.plus(diag));
            list.add(coord.plus(hor));
        }

        if (coord.y < height - 1)
            list.add(coord.plus(ver));

        return list;
    }

    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        int j = 0;

        for (; j < height; ++j) {
            for (int i = 0; i < height - 1 - j; ++i)
                builder.append("  ");

            for (int i = 0; i <= Math.min(j, width); ++i) {
                appendObject(builder, new Coord(i, j - i));
            }
            builder.append('\n');
        }

        for (; j < width + height - 1; ++j) {
            int i = 0;
            for (; i <= j - height; ++i)
                builder.append("  ");

            for (; i < width; ++i) {
                appendObject(builder, new Coord(i, j - i));
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    private void appendObject(StringBuilder builder, Coord coord) {
        FieldObject o = getObject(coord);
        builder.append(o == null ? '.' : o.getChar());
        builder.append("   ");
    }
}
