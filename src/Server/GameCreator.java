package server;

import server.model.Game;

//This class is responsible for starting games
public class GameCreator {

    //this player is currently waiting for another player in order to start a game
    private static PlayerHandler waitingPlayer = null;

    public static void tryPlay(PlayerHandler player) {
        System.out.println(player.getPlayerName()+" wants to play...");
        if (waitingPlayer != null) {
            new Game(waitingPlayer, player);
            waitingPlayer = null;
        } else {
            waitingPlayer = player;
        }
    }

}
