package ru.spbau.kononenko.drunkgame;

import ru.spbau.kononenko.drunkgame.game.Game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        for (int i = 0; i < 1000; ++i) {
            //if (i == 200 || i == 300 || i == 500)
                game.output();
            game.update();
        }
    }
}
