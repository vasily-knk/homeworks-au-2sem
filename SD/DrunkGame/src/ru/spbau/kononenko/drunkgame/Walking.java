package ru.spbau.kononenko.drunkgame;

abstract class Walking/*<Coord>*/ implements FieldObject, DynamicObject {
    private final Field/*<Coord>*/ field;
	private Coord coord;

    Walking(Field/*<Coord>*/ field, Coord coord) {
        this.field = field;
        this.coord = coord;

        if (field.getObject(coord) != null)
            throw new FieldOccupiedException(coord.toString());

        field.setObject(coord, this);
    }
	
	public Coord getCoord() {
        return coord;
	}
    
    public Field getField() {
        return field;
    }
	
	protected void moveTo(Coord c) {
        if (c == coord)
            return;

        if (field.getObject(c) != null)
            throw new FieldOccupiedException(c.toString());

        field.setObject(coord, null);
        coord = c;
		field.setObject(coord, this);
	}
}
