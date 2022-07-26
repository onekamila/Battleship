package game;


import game.GameBoard.Square;


/**
 * Represents a single move
 *
 * @author Garrett Kamila Crayton
 * @version 0.0.0
 */
public class Move
{
    private Square square;
    private Result result;
    private boolean win;
    
    
    /**
     * Class constructor
     *
     * @param square the <code>Square</code> for this <code>Move</code>
     * @param result the <code>Result</code> of this <code>Move</code>
     */
    public Move(Square square, Result result)
    {
        this.square = square;
        this.result = result;
        this.win = false;
    }
    
    
    /**
     * Returns the <code>Square</code> for this <code>Move</code>
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
        return this.result;
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
        String outStr = "-" + this.result.name().charAt(0);
        
        if((result == Result.HIT) || (result == Result.SUNK))
        {
            outStr += getSquare().getShip().getName().charAt(0);
        }
        
        return outStr;
    }
}