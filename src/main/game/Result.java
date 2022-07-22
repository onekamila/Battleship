package game;


/**
 * Represents all possible results from a <code>Move</code>
 */
public enum Result
{
    OPEN('-'),
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