package Battleship.game;


/**
 * Represents all possible results from a <code>Move</code>
 *
 * @author Garrett Kamila Crayton
 * @version 0.0.0
 */
public enum Result
{
    HIT('X'),
    MISS('O'),
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