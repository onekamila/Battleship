package Battleship.game.GameBoard;


import Battleship.game.Result;
import Battleship.game.Ships.Ship;


/**
 * Represents a single square on a <code>Board</code>
 *
 * @author Garrett Kamila Crayton
 * @version 0.0.0
 */
public class Square
{
    private String position;
    private Result result;
    private Ship ship;
    
    
    /**
     * Class constructor
     *
     * @param position the position of this <code>Square</code> within its parent <code>Board</code>
     */
    public Square(String position)
    {
        this.result = null;
        this.ship = null;
        this.position = position;
    }
    
    
    /**
     * Returns the <code>Result</code> for this <code>Square</code>
     *
     * @return the <code>Result</code> for this <code>Square</code>
     */
    public Result getResult()
    {
        return this.result;
    }
    
    /**
     * Returns the position of this <code>Square</code>
     *
     * @return the position coordinate for this <code>Square</code> as a <code>String</code>
     */
    public String getPosition()
    {
        return this.position;
    }
    
    /**
     * Returns the <code>Ship</code> placed at this <code>Square</code>, if any
     *
     * @return the <code>Ship</code> placed at this <code>Square</code>, <code>null</code> if no ship placed
     */
    public Ship getShip()
    {
        return ship;
    }
    
    /**
     * Set a given <code>Ship</code> on this <code>Square</code>
     *
     * @param ship the <code>Ship</code> to be placed
     */
    public void setShip(Ship ship)
    {
        this.ship = ship;
    }
    
    
    /**
     * Make a move on this <code>Square</code>
     *
     * @return the <code>Result</code> of the move
     */
    public Result move()
    {
        if(ship != null)
        {
            ship.hit();
            if(ship.sunk())
            {
                result = Result.SUNK;
            }
            else
            {
                result = Result.HIT;
            }
        }
        else
        {
            result = Result.MISS;
        }
        return result;
    }
    
    /**
     * Resets this <code>Square</code> to its original (<code>Open</code>) state.
     *
     * This is mostly used for testing
     */
    public void reset()
    {
        this.result = null;
    }
    
    /**
     * Returns a <code>String</code> representation of this of the <code>Board</code>
     *
     * @return a <code>String</code> representation of the <code>Board</code>
     */
    public String toString()
    {
        if(result == null)
        {
            if((ship != null))
            {
                return ship.toString();
            }
            else
            {
                return "-";
            }
        }
        else
        {
            return result.toString();
        }
    }
}