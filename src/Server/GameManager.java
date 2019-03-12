package server;

import shared.model.Game;
import shared.model.RequestCodes;

public class GameManager {
    private PlayerHandler player1;
    private PlayerHandler player2;
    private Game game;
    private Game.Sign turn; //indicates who's turn is it now;

    public GameManager(PlayerHandler player1, PlayerHandler player2) {
        game = new Game();
        player1.joinGame(Game.Sign.CROSS);
        player2.joinGame(Game.Sign.ZERO);
    }

    public void setPlayerTurn(Game.Sign sign) {
        if (sign == Game.Sign.ZERO || sign == Game.Sign.CROSS) {
            this.turn = sign;
        }
    }

    public void checkIfPlacePossible(int row, int column, Game.Sign sign) {
        if (turn != sign) {
            if (sign == Game.Sign.CROSS)
                player1.send(RequestCodes.NOT_YOUR_TURN);
            else
                player2.send(RequestCodes.NOT_YOUR_TURN);
        }
        if (game.getPlace(row, column) == Game.Sign.EMPTY) {
            place(row, column, sign);
        } else if (sign == Game.Sign.CROSS)
            player1.send(RequestCodes.NOT_YOUR_TURN);
        else
            player2.send(RequestCodes.NOT_YOUR_TURN);

    }

    public void place(int row, int column, Game.Sign sign) {
        game.place(row, column, sign);
        player1.send(RequestCodes.PLAYER_PLACED + "|" + row + "|" + column + "|" + (sign == Game.Sign.ZERO ? "O" : "X"));
        player2.send(RequestCodes.PLAYER_PLACED + "|" + row + "|" + column + "|" + (sign == Game.Sign.ZERO ? "O" : "X"));
        if (sign == Game.Sign.CROSS)
            setPlayerTurn(Game.Sign.ZERO);
        else
            setPlayerTurn(Game.Sign.CROSS);
    }

    public boolean isWin() {

        for (int i = 0; i < 3; i++) {
            if (game.getPlace(i, 0) == game.getPlace(i, 1) && game.getPlace(i, 0) == game.getPlace(i, 2)) {
                player1.send(RequestCodes.WIN + "|" + (game.getPlace(i, 0) == Game.Sign.ZERO ? "O" : "X"));
                player2.send(RequestCodes.WIN + "|" + (game.getPlace(i, 0) == Game.Sign.ZERO ? "O" : "X"));
                return true;
            }

        }

        for (int i = 0; i < 3; i++) {
            if (game.getPlace(0, i) == game.getPlace(1, i) && game.getPlace(0, i) == game.getPlace(2, i)) {
                player1.send(RequestCodes.WIN + "|" + (game.getPlace(0, i) == Game.Sign.ZERO ? "O" : "X"));
                player2.send(RequestCodes.WIN + "|" + (game.getPlace(0, i) == Game.Sign.ZERO ? "O" : "X"));
                return true;
            }

        }

        if (game.getPlace(0, 0) == game.getPlace(1, 1) && game.getPlace(0, 0) == game.getPlace(2, 2)) {
            player1.send(RequestCodes.WIN + "|" + (game.getPlace(0, 0) == Game.Sign.ZERO ? "O" : "X"));
            player2.send(RequestCodes.WIN + "|" + (game.getPlace(0, 0) == Game.Sign.ZERO ? "O" : "X"));
            return true;
        }

        if (game.getPlace(0, 2) == game.getPlace(1, 1) && game.getPlace(0, 2) == game.getPlace(2, 0)) {
            player1.send(RequestCodes.WIN + "|" + (game.getPlace(0, 2) == Game.Sign.ZERO ? "O" : "X"));
            player1.send(RequestCodes.WIN + "|" + (game.getPlace(0, 2) == Game.Sign.ZERO ? "O" : "X"));
            return true;
        }


        return false;
    }


}
