package shared.model;

//request codes that can be exchanged between clients and server
public class RequestCodes {
    public static final String PLAY = "can I play pls?";
    public static final String TRY_PLACE = "can I place here pls?";//when a player sends a try place request to the server
    public static final String WIN = "player won";
    public static final String JOINED_GAME = "you joined game";
    public static final String WAITING_FOR_OPPONENT = "waiting for one more player";
    public static final String PLAYER_PLACED = "place here";//when a player placed (sent by server to the players)
    public static final String NOT_YOUR_TURN = "Not your turn!";//Can't place because it's not this player's turn
    public static final String PLACE_TAKEN = "Place taken!";//Can't place because space is taken
    public static final String FULL_BOARD = "It's a draw!";
}
