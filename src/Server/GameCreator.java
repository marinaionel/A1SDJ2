package server;

import shared.model.Game;

//This class is responsible for starting games
public class GameCreator {

    //this player is currently waiting for another player in order to start a game
    private static PlayerHandler waitingPlayer = null;

    public static boolean tryPlay(PlayerHandler player) {
        if (waitingPlayer != null) {
            System.out.println(player.getPlayerName() + " wants to play...");
            System.out.println("Game started");
            new GameManager(waitingPlayer, player);
            waitingPlayer = null;
            return true;
        }
        System.out.println(player.getPlayerName() + " wants to play...");
        waitingPlayer = player;
        return false;
    }

}
