package Battleship.game;


import Battleship.game.GameBoard.Square;


/**
 * Represents a single move
 *
 * @see Square
 * @see Result
 *
 * @author Garrett Kamila Crayton
 * @version 0.2.1
 * @since 0.0.0
 */
public class Move
{
    private Square square;
    private boolean win;
    
    
    /**
     * Class constructor
     *  @param square the <code>Square</code> for this <code>Move</code>
     */
    public Move(Square square)
    {
        this.square = square;
        this.win = false;
    }
    
    
    /**
     * Returns the <code>Square</code> for this <code>Move</code>
     *
     * @return the <code>Square</code> for this <code>Move</code>
     */
    public Square getSquare()
    {
        return this.square;
    }
    
    /**
     * Returns the <code>Result</code> of this <code>Move</code>
     
     * @return the <code>Result</code> of this <code>Move</code>
     */
    public Result getResult()
    {
        return square.getResult();
    }
    
    
    /**
     * Set this <code>Move</code> as the winning <code>Move</code>
     */
    public void setWin()
    {
        this.win = true;
    }
    
    
    /**
     * Returns a <code>String</code> representation of this of the <code>Move</code>
     *
     * @return a <code>String</code> representation of the <code>Move</code>
     */
    @Override
    public String toString()
    {
        String outStr = "";
        outStr += square.getPosition();
        outStr += resultStr();
        
        if(win)
        {
            outStr += "#";
        }
        
        return outStr;
    }
    
    private String resultStr()
    {
        Result result = square.getResult();
        String outStr = "-" + result.name().charAt(0);
        
        if((result == Result.HIT) || (result == Result.SUNK))
        {
            outStr += getSquare().getShip().getName().charAt(0);
        }
        
        return outStr;
    }
}