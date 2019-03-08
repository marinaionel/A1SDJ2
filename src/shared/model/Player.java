package shared.model;

public class Player {
    private String username;
    private int score;

    public Player(String username) {
        this.username = username;
        score = 0;
    }

    public void incScore() {
        score++;
    }

    public int getScore() {
        return score;
    }

    public String getUsername() {
        return username;
    }
}
