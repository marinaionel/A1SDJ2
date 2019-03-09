package shared.model;

//request codes that can be exchanged between clients and server
public class RequestCodes {
    public static final String PLAY = "can I play pls?";
    public static final String TRY_PLACE = "can I place here pls?";//when a player sends a try place request to the server
    public static final String WIN = "can I place here pls?";
    public static final String JOINED_GAME = "you joined game";
    public static final String WAITING_FOR_OPPONENT = "waiting for one more player";
    public static final String OPPONENT_FOUND = "opponent found";
    public static final String CANT_PLACE = "you cannot place";//2 reasons: not your turn or invalid position
    public static final String PLAYER_PLACED = "place here";//when a player placed (sent by server to the players)
    public static final String GAME_FINISHED = "game finished";


}
