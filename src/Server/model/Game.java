package server.model;

import server.PlayerHandler;

public class Game {
    public enum Sign {
        EMPTY,
        CROSS,
        ZERO
    }

    private Sign[][] gameArray;

    public Game(PlayerHandler player1, PlayerHandler player2) {
        gameArray = new Sign[][]{
                {Sign.EMPTY, Sign.EMPTY, Sign.EMPTY},
                {Sign.EMPTY, Sign.EMPTY, Sign.EMPTY},
                {Sign.EMPTY, Sign.EMPTY, Sign.EMPTY}};
    }

    public void place(int row, int column, Sign sign) {
        gameArray[row][column] = sign;
    }




    public Sign getPlace(int row, int column) {
        return gameArray[row][column];
    }
}
