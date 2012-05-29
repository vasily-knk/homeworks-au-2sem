package ru.spbau.kononenko.task3;

import java.util.LinkedList;
import java.util.List;

/**
 * Main class
 * @author Vasily Kononenko
 * @version %I%, %G%
 */
public class Main {
    /**
     * program entry point
     * @param args command line args
     */
    public static void main(String[] args) {
        String str = "5 * 6 + 18";
        List<Block> list = splitString(str);
        Node root = splitBlockList(list);
        System.out.println(root.toString());
    }

    public static List<Block> splitString(String str) {
        List<Block> list = new LinkedList<Block>();

        int num = 0;
        boolean collectingNumber = false;
        for (char c : str.toCharArray()) {
            if (c >= '0' && c <= '9') {
                num *= 10;
                num += c - '0';
                collectingNumber = true;
                continue;
            }

            if (collectingNumber) {
                list.add(new NumNode(num));
                collectingNumber = false;
                num = 0;
            }

            if (c == ' ')
                continue;

            boolean found = false;
            for (OpType type : OpType.values()) {
                if (type.getChar() == c) {
                    list.add(new OpBlock(type));
                    found = true;
                    break;
                }
            }

            if (found)
                continue;
            
            throw new UnknownCharExpression(c);

        }
        if (collectingNumber)
            list.add(new NumNode(num));

        return list;
    }
    
    public static Node splitBlockList(List<Block> list)
    {
        for (Block.Stage stage : Block.Stage.values()) {
            int index = 0;
            for (Block block : list) {
                Node node = block.tryToSplit(list, index, stage);
                if (node != null)
                    return node;

                ++index;
            }
        }
        throw new SyntaxErrorException();
    }
    
}
