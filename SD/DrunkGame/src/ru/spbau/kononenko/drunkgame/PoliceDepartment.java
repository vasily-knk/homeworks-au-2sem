package ru.spbau.kononenko.drunkgame;

public class PoliceDepartment implements DrunkReportInterface, FieldImageDecoration {
    private final Field field;
    private final FieldGeometry geometry;
    private final Coord coord;
    
    public PoliceDepartment(Field field, FieldGeometry geometry, FieldImage image, Coord coord)
    {
        this.field = field;
        this.geometry = geometry;
        this.coord = coord;
        image.addSpot(coord, this);
    }

    @Override
    public void drunkReport(Drunk drunk) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public char getChar() {
        return 'П';
    }
}
