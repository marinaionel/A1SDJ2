package server;

import shared.model.Player;

import java.util.ArrayList;

public class PlayerList {

    private static ArrayList<Player> players = new ArrayList<>();

    public static void addPlayer(Player p) {
        players.add(p);
    }

    public static Player[] getPlayers() {
        players.sort((p1, p2) -> p2.getScore() - p1.getScore());
        if (players.size() > 10) {
            return players.subList(0, 11).toArray(new Player[]{});
        }
        return players.toArray(new Player[]{});
    }

    public static void removePlayer(Player player) {
        players.remove(player);
    }
}
