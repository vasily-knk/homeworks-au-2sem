package ru.spbau.kononenko.drunkgame;

import java.util.ArrayList;
import java.util.List;

public class RectField implements FieldGeometry, Field, FieldImage {
    private final int width, height;
    private final FieldObject[][] data;
    private final FieldImageDecoration[] leftDecorations, topDecorations, rightDecorations, bottomDecorations;

    public RectField(int width, int height) {
        this.width = width;
        this.height = height;
        
        this.data = new FieldObject[width][height];
        for (int j = 0; j < height; ++j)
            for (int i = 0; i < width; ++i)
                this.data[i][j] = null;

        this.topDecorations = new FieldImageDecoration[width];
        this.bottomDecorations = new FieldImageDecoration[width];
        for (int i = 0; i < width; ++i)
            this.topDecorations[i] = this.bottomDecorations[i] = null;
        
        this.leftDecorations = new FieldImageDecoration[height];
        this.rightDecorations = new FieldImageDecoration[height];
        for (int j = 0; j < height; ++j)
            this.leftDecorations[j] = this.rightDecorations[j] = null;
    }

    @Override
    public FieldObject getObject(Coord coord) {
        assertInside(coord);
        return data[coord.x][coord.y];
    }

    @Override
    public void setObject(Coord coord, FieldObject object) {
        assertInside(coord);
        data[coord.x][coord.y] = object;
    }

    @Override
    public void addSpot(Coord coord, FieldImageDecoration decoration) {
        assertInside(coord);
        if (coord.x == 0)
            leftDecorations[coord.y] = decoration;
        if (coord.y == 0)
            topDecorations[coord.x] = decoration;
        if (coord.x == width - 1)
            rightDecorations[coord.y] = decoration;
        if (coord.y == height - 1)
            bottomDecorations[coord.x] = decoration;
        
    }

    @Override
    public void print() {
        System.out.print(' ');
        for (int i = 0; i < width; ++i)
            System.out.print(topDecorations[i] != null ? topDecorations[i].getChar() : ' ');
        System.out.println(' ');

        for (int j = 0; j < height; ++j) {
            System.out.print(leftDecorations[j] != null ? leftDecorations[j].getChar() : ' ');
            for (int i = 0; i < width; ++i)
                System.out.print(data[i][j] != null ? data[i][j].getChar() : '.');
            System.out.println(rightDecorations[j] != null ? rightDecorations[j].getChar() : ' ');
        }

        System.out.print(' ');
        for (int i = 0; i < width; ++i)
            System.out.print(bottomDecorations[i] != null ? bottomDecorations[i].getChar() : ' ');
        System.out.println(' ');
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
    public int getDist(Coord coord1, Coord coord2) {
        return Math.abs(coord2.x - coord1.x) + Math.abs(coord2.y - coord1.y);
    }

    @Override
    public boolean isInside(Coord coord) {
        return (coord.x >= 0 && coord.y >= 0 && coord.x < width && coord.y < height);
    }

    private void assertInside(Coord coord) {
        if(!isInside(coord))
            throw new FieldOutOfRangeException(coord.toString());
    }
}
