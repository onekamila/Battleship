package Battleship.GameIO.Views;


import Battleship.game.Game;
import Battleship.game.GameBoard.Board;
import Battleship.game.GameBoard.OpponentBoard;


/**
 * Displays a player's <code>Board</code> and <code>OpponentBoard</code>
 *
 * @see Board
 * @see OpponentBoard
 *
 * @author Garrett Kamila Crayton
 * @version 0.2.1
 * @since 0.0.0
 */
public class BoardView extends View
{
    /**
     * Class constructor
     *
     * @param game the <code>Game</code> this <code>View</code> is based on
     */
    public BoardView(Game game)
    {
        super(game);
    }
    
    /**
     * Returns a <code>String</code> representation of this <code>View</code>
     *
     * @see Board#toString()
     * @see OpponentBoard#toString()
     *
     * @return a <code>String</code> representation of this <code>View</code>
     */
    @Override
    public String toString()
    {
        updatePlayers();
    
        Board board = player.getBoard();
        OpponentBoard oBoard = player.getOpponentBoard();
    
        String outStr = "\t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t\n";
        outStr += " -----------------------\t -----------------------\n";
        
        String[] pBoardStr = board.toString().split("\n");
        String[] oBoardStr = oBoard.toString().split("\n");
    
        for(int i = 0; i < 11; i++)
        {
            outStr += pBoardStr[i] + "\t" + oBoardStr[i] + "\n";
        }
        
        return outStr;
    }
}