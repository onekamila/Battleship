package GameIO.Views;


import game.GameBoard.Board;
import game.GameBoard.OpponentBoard;


/**
 * Displays a player's <code>Board</code> and <code>OpponentBoard</code>
 *
 * @author Garrett Kamila Crayton
 * @version 0.0.0
 */
public class BoardView
{
    public Board board;
    public OpponentBoard oBoard;
    
    
    /**
     * Class constructor
     *
     * @param board the <code>Board</code> of the current <code>Player</code>
     * @param oBoard the <code>OpponentBoard</code> of the current <code>Opponent</code>
     */
    public BoardView(Board board, OpponentBoard oBoard)
    {
        this.board = board;
        this.oBoard = oBoard;
    }
    
    
    /**
     * Returns a <code>String</code> representation of this view
     *
     * @return a <code>String</code> representation of this view
     */
    public String toString()
    {
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