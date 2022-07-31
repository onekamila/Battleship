package Battleship.game.GameBoard;


import Battleship.game.Move;


/**
 * Represents a game <code>Board</code> for a single player. This model displays the player's moves
 *
 * @see Board
 * @see Move
 *
 * @author Garrett Kamila Crayton
 * @version 0.1.0
 * @since 0.0.0
 */
public class OpponentBoard extends Board
{
    /**
     * Class Constructor
     */
    public OpponentBoard(Board board)
    {
        super(board.getSquares());
    }
    
    
    /**
     * Performs the desired move on the specified <code>Square</code>
     *
     * @see Board#move(int, int)
     *
     * @param row the row of the desired move
     * @param col the column of the desired move
     * @return the <code>Move</code> object representing the desired move
     */
    @Override
    public Move move(int row, int col)
    {
        return super.move(row, col);
    }
    
    
    /**
     * Returns a <code>String</code> representation of this of the <code>Board</code>
     *
     * @see Board#toString()
     *
     * @return a <code>String</code> representation of the <code>Board</code>
     */
    @Override
    public String toString()
    {
        String originStr = super.toString();
        
        String header = originStr.split("\n")[0];
        
        String tempStr = originStr.replaceAll("[ABCDS]", "-");
        
        String[] tempStrArr = tempStr.split("\n");
        tempStrArr[0] = header;
        
        
        String outStr = String.join("\n", tempStrArr);
        
        return outStr;
    }
}