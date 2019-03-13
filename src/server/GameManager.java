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
        this.player1 = player1;
        this.player2 = player2;
        player1.joinGame(Game.Sign.CROSS, this);
        player2.joinGame(Game.Sign.ZERO, this);
        turn = Game.Sign.CROSS;
    }

    public void tryPlace(int row, int column, Game.Sign sign) {
        if (turn != sign) {
            if (sign == Game.Sign.CROSS)
                player1.send(RequestCodes.NOT_YOUR_TURN);
            else
                player2.send(RequestCodes.NOT_YOUR_TURN);
            return;
        }
        if (game.getPlace(row, column) == Game.Sign.EMPTY) {
            place(row, column, sign);
        } else {
            if (sign == Game.Sign.CROSS)
                player1.send(RequestCodes.PLACE_TAKEN);
            else
                player2.send(RequestCodes.PLACE_TAKEN);
        }
    }

    public void place(int row, int column, Game.Sign sign) {
        game.place(row, column, sign);
        player1.send(RequestCodes.PLAYER_PLACED + "|" + row + "|" + column + "|" + (sign == Game.Sign.ZERO ? "O" : "X"));
        player2.send(RequestCodes.PLAYER_PLACED + "|" + row + "|" + column + "|" + (sign == Game.Sign.ZERO ? "O" : "X"));
        turn = (turn == Game.Sign.CROSS ? Game.Sign.ZERO : Game.Sign.CROSS);
    }

    public boolean isWin() {

        for (int i = 0; i < 3; i++) {
            if (game.getPlace(i, 0) == game.getPlace(i, 1) && game.getPlace(i, 0) == game.getPlace(i, 2) && game.getPlace(i, 0) != Game.Sign.EMPTY) {
                player1.send(RequestCodes.WIN + "|" + (game.getPlace(i, 0) == Game.Sign.ZERO ? "O" : "X"));
                player2.send(RequestCodes.WIN + "|" + (game.getPlace(i, 0) == Game.Sign.ZERO ? "O" : "X"));
                if(game.getPlace(i, 0) ==Game.Sign.CROSS)
                    player1.incPlayerScore();
                else
                    player2.incPlayerScore();
                return true;
            }

        }

        for (int i = 0; i < 3; i++) {
            if (game.getPlace(0, i) == game.getPlace(1, i) && game.getPlace(0, i) == game.getPlace(2, i) && game.getPlace(0, i) != Game.Sign.EMPTY) {
                player1.send(RequestCodes.WIN + "|" + (game.getPlace(0, i) == Game.Sign.ZERO ? "O" : "X"));
                player2.send(RequestCodes.WIN + "|" + (game.getPlace(0, i) == Game.Sign.ZERO ? "O" : "X"));
                if(game.getPlace(0, i) ==Game.Sign.CROSS)
                    player1.incPlayerScore();
                else
                    player2.incPlayerScore();
                return true;
            }

        }

        if (game.getPlace(0, 0) == game.getPlace(1, 1) && game.getPlace(0, 0) == game.getPlace(2, 2) && game.getPlace(2, 2) != Game.Sign.EMPTY) {
            player1.send(RequestCodes.WIN + "|" + (game.getPlace(0, 0) == Game.Sign.ZERO ? "O" : "X"));
            player2.send(RequestCodes.WIN + "|" + (game.getPlace(0, 0) == Game.Sign.ZERO ? "O" : "X"));
            if(game.getPlace(0, 0) ==Game.Sign.CROSS)
                player1.incPlayerScore();
            else
                player2.incPlayerScore();
            return true;
        }
        if (game.getPlace(0, 2) == game.getPlace(1, 1) && game.getPlace(0, 2) == game.getPlace(2, 0) && game.getPlace(2, 0) != Game.Sign.EMPTY) {
            player1.send(RequestCodes.WIN + "|" + (game.getPlace(0, 2) == Game.Sign.ZERO ? "O" : "X"));
            player1.send(RequestCodes.WIN + "|" + (game.getPlace(0, 2) == Game.Sign.ZERO ? "O" : "X"));
            if(game.getPlace(0, 2) ==Game.Sign.CROSS)
                player1.incPlayerScore();
            else
                player2.incPlayerScore();
            return true;
        }
        return false;
    }


}
