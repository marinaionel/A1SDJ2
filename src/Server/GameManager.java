package server;

import shared.model.Game;
import shared.model.RequestCodes;

public class GameManager {


    private PlayerHandler player1;
    private PlayerHandler player2;
    private Game game;

    private Game.Sign turn; //indicates who's turn is it now;


    public GameManager(PlayerHandler player1, PlayerHandler player2) {


        player1.joinGame(Game.Sign.CROSS);
        player2.joinGame(Game.Sign.ZERO);
    }

    public void setPlayerTurn(Game.Sign sign)
    {
        if(sign == Game.Sign.ZERO || sign == Game.Sign.CROSS)
        {
            this.turn = sign;
        }
    }

    public String checkIfPlacePossible(int row, int column, Game.Sign sign)
    {
        if(turn != sign)
        {
            return RequestCodes.NOT_YOUR_TURN;
        }

        if(game.getPlace(row, column) == Game.Sign.EMPTY)
        {
            game.place(row, column, sign);
            if(sign == Game.Sign.CROSS)
                setPlayerTurn(Game.Sign.ZERO);
            else
                setPlayerTurn(Game.Sign.CROSS);



            if(isWin() != Game.Sign.EMPTY)
            return RequestCodes.WIN;
            else
                return RequestCodes.PLAYER_PLACED;
        }
        return RequestCodes.PLACE_TAKEN;

    }

    public Game.Sign isWin()
    {

        for(int i=0; i < 3; i++)
        {
            if(game.getPlace(i, 0) == game.getPlace(i, 1) && game.getPlace(i,0)== game.getPlace(i,2))
                return game.getPlace(i,0);

        }

        for(int i=0; i < 3; i++)
        {
            if(game.getPlace(0, i) == game.getPlace(1, i) && game.getPlace(0,i)== game.getPlace(2,i))
                return game.getPlace(0,i);

        }

        if(game.getPlace(0,0) == game.getPlace(1,1) && game.getPlace(0,0) == game.getPlace(2,2))
            return game.getPlace(0,0);

        if(game.getPlace(0,2) == game.getPlace(1,1) && game.getPlace(0,2) == game.getPlace(2,0))
            return game.getPlace(0,2);


        return Game.Sign.EMPTY;
    }







}
