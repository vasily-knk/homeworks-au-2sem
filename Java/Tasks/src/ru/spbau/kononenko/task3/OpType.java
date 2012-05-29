package ru.spbau.kononenko.task3;

public class OpType {
    private char c;
    private Block.Stage stage;

    public OpType(char c, Block.Stage stage) {
        this.c = c;
        this.stage = stage;
    }
    
    public char getChar() {
        return c;
    }

    public Block.Stage getStage() {
        return stage;
    }

    public static OpType PLUS = new OpType('+', Block.Stage.ADDITIVE);
    public static OpType MINUS = new OpType('-', Block.Stage.ADDITIVE);
    public static OpType MULT = new OpType('*', Block.Stage.MULTIPLICATIVE);
    public static OpType DIV = new OpType('/', Block.Stage.MULTIPLICATIVE);

    static OpType[] values() {
        return new OpType[]{PLUS, MINUS, MULT, DIV};
    }
}
