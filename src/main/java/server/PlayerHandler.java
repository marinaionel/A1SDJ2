package server;

import shared.model.Game;
import shared.model.Player;
import shared.model.RequestCodes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PlayerHandler implements Runnable {

    private Socket socket;
    private boolean isInGame;
    private Game.Sign sign;
    private ObjectInputStream socketIn = null;
    private ObjectOutputStream socketOut = null;
    private Player player;
    private boolean tryPlayResult;
    private GameManager gameManager;

    public PlayerHandler(Socket socket) {
        this.socket = socket;
        player = new Player();
        PlayerList.addPlayer(player);
    }

    public void joinGame(Game.Sign sign, GameManager gameManager) {
        this.sign = sign;
        this.gameManager = gameManager;
        isInGame = true;
        try {
            socketOut.writeUTF(RequestCodes.JOINED_GAME + "|" + (sign == Game.Sign.ZERO ? "O" : "X"));
            socketOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String request = "";
        try {
            socketIn = new ObjectInputStream(socket.getInputStream());
            socketOut = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }
        while (true) {
            if (!request.contains(RequestCodes.PLAY))
                try {
                    request = socketIn.readUTF();
                } catch (IOException e) {
                    return;
                }
            System.out.println(request);

            if (request.contains(RequestCodes.PLAY)) {
                player.setPlayerName(request.split("\\|")[1]); //when splitting the request message the last parameter is the player's name
                tryPlayResult = GameCreator.tryPlay(this);

                //if we did not found an opponent we notify the client to wait until an opponent joins
                if (!tryPlayResult) {
                    try {
                        socketOut.writeUTF(RequestCodes.WAITING_FOR_OPPONENT);
                        socketOut.flush();
                        request = "";
                    } catch (IOException e) {
                        return;
                    }
                }
            }

            if (isInGame) {
                do {
                    //this if is for avoiding double click when the X player starts the game
                    if (!request.contains(RequestCodes.TRY_PLACE))
                        try {
                            request = socketIn.readUTF();
                        } catch (IOException e) {
                            return;
                        }
                    if (!gameManager.isGameOn())
                        break;
                    System.out.println(request);
                    if (request.contains(RequestCodes.TRY_PLACE)) {
                        String[] array = request.split("\\|");
                        gameManager.tryPlace(Integer.parseInt(array[1]), Integer.parseInt(array[2]), (array[3].equals("X") ? Game.Sign.CROSS : Game.Sign.ZERO));
                    }
                    request = "";
                } while (!gameManager.isWin() && !gameManager.isFull());
            }
            isInGame = false;
            gameManager = null;
            if (request.contains(RequestCodes.GET_RESULTS_TABLE)) {
                try {
                    socketOut.writeObject(PlayerList.getPlayers());
                    socketOut.flush();
                } catch (IOException e) {
                    PlayerList.removePlayer(player);
                    return;
                }
            }
        }
    }

    public String getPlayerName() {
        return player.getPlayerName();
    }

    public void incPlayerScore() {
        player.incScore();
    }

    public void send(String message) {
        try {
            socketOut.writeUTF(message);
            socketOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
