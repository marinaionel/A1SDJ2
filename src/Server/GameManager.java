package server;

import shared.model.Game;

public class GameManager {
    public GameManager(PlayerHandler player1, PlayerHandler player2) {
        player1.joinGame(Game.Sign.CROSS);
        player2.joinGame(Game.Sign.ZERO);
    }
}
