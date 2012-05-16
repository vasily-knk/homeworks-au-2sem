package ru.spbau.kononenko.drunkgame.Field;

public abstract class Field2D extends Field {
    protected final int width, height;
    private final FieldObject[][] data;

    public Field2D(int width, int height) {
        this.width = width;
        this.height = height;

        this.data = new FieldObject[width][height];
        for (int j = 0; j < height; ++j)
            for (int i = 0; i < width; ++i)
                this.data[i][j] = null;
    }


    @Override
    public boolean isInside(Coord coord) {
        return (coord.x >= 0 && coord.y >= 0 && coord.x < width && coord.y < height);
    }

    @Override
    public FieldObject getObject(Coord coord) {
        assertInside(coord);
        return data[coord.x][coord.y];
    }

    @Override
    public void setObject(Coord coord, FieldObject object) {
        assertInside(coord);
        if (data[coord.x][coord.y] != null)
            throw new FieldOccupiedException(coord.toString());
        data[coord.x][coord.y] = object;
    }

    @Override
    public void removeObject(Coord coord) {
        assertInside(coord);
        if (data[coord.x][coord.y] == null)
            throw new FieldFreeException(coord.toString());
        data[coord.x][coord.y] = null;
    }

    public void assertInside(Coord coord) {
        if(!isInside(coord))
            throw new FieldOutOfRangeException(coord.toString());
    }

}
