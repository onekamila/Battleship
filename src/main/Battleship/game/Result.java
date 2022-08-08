package Battleship.game;


/**
 * Represents all possible results from a <code>Move</code>
 *
 * @author Garrett Kamila Crayton
 * @version 0.2.2
 * @since 0.0.0
 */
public enum Result
{
    /**
     * Represents a hit on a <code>Ship</code>
     */
    HIT('X'),
    /**
     * Represents a miss
     */
    MISS('O'),
    /**
     * Represents a <code>Ship</code> was hit and sunk
     */
    SUNK('X');
    
    
    private final char outChar;
    
    
    private Result(char outChar)
    {
        this.outChar = outChar;
    }
    
    
    /**
     * <code>String</code> representation of this <code>Result</code>
     *
     * @return the character code of this <code>Result</code>
     */
    @Override
    public String toString()
    {
        return Character.toString(outChar);
    }
}