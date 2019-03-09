package server;

import shared.model.Game;
import shared.model.Player;

public class GameManager {


    private Player player1;
    private Player player2;
    private Game game;

    private Game.Sign turn; //indicates who's turn is it now;



    public GameManager(PlayerHandler player1, PlayerHandler player2) {



        player1.joinGame(Game.Sign.CROSS);
        player2.joinGame(Game.Sign.ZERO);
    }


}
