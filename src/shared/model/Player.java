package shared.model;

import java.io.Serializable;

public class Player implements Serializable {
    private String playerName;
    private int score;

    public Player() {
        playerName = "";
        score = 0;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void incScore() {
        score++;
    }

    public int getScore() {
        return score;
    }

    public String getPlayerName() {
        return playerName;
    }
}
