package ru.spbau.kononenko.drunkgame;

import java.util.ArrayList;
import java.util.List;

public class RectField implements FieldGeometry<Coord2d>, Field<Coord2d> {
    private final int width, height;
    private final FieldObject[][] data;

    public RectField(int width, int height) {
        this.width = width;
        this.height = height;
        this.data = new FieldObject[width][height];
        for (int j = 0; j < height; ++j)
            for (int i = 0; i < width; ++i)
                this.data[i][j] = null;
    }

    @Override
    public FieldObject getObject(Coord2d coord) {
        assertInside(coord);
        return data[coord.x][coord.y];
    }

    @Override
    public void setObject(Coord2d coord, FieldObject object) {
        assertInside(coord);
        data[coord.x][coord.y] = object;
    }

    @Override
    public void print() {
        for (int j = 0; j < height; ++j) {
            for (int i = 0; i < width; ++i)
                System.out.print(data[i][j] != null ? data[i][j].getChar() : '.');
            System.out.println();
        }
    }

    @Override
    public List<Coord2d> getAdjacent(Coord2d coord) {
        assertInside(coord);
        ArrayList<Coord2d> list = new ArrayList<Coord2d> ();

        Coord2d hor = new Coord2d (1, 0);
        Coord2d ver = new Coord2d (0, 1);

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
    public int getDist(Coord2d coord1, Coord2d coord2) {
        return Math.abs(coord2.x - coord1.x) + Math.abs(coord2.y - coord1.y);
    }

    @Override
    public boolean isInside(Coord2d coord) {
        return (coord.x >= 0 && coord.y >= 0 && coord.x < width && coord.y < height);
    }

    private void assertInside(Coord2d coord) {
        if(!isInside(coord))
            throw new FieldOutOfRangeException(coord.toString());
    }
}
