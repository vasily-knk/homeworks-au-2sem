package ru.spbau.kononenko.drunkgame;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        for (int i = 0; i < 1000; ++i) {
            game.update();
            //if (i % 100 == 0)
                game.output();
        }
    }
}
