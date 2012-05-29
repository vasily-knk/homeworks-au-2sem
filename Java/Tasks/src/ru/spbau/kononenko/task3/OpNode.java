package ru.spbau.kononenko.task3;

public class OpNode implements Node {
    private OpType type;
    private Node left, right;
    
    public OpNode(OpType type, Node left, Node right) {
        this.type = type;
        this.left = left;
        this.right = right;
    }

}
