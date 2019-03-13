package server;

import shared.model.Player;

import java.util.ArrayList;

public class PlayerList {

    static ArrayList<Player> players=new ArrayList<>();

    public static void addPlayer(Player p){
        players.add(p);
    }

    public static Player[] getPlayers(){
        return players.toArray(new Player[]{});
    }

}
