package game.Ships;


/**
 * Represents a Submarine
 */
public class Submarine extends Ship
{
    private static final String NAME = "Submarine";
    private static final char SYMBOL = 'S';
    private static final int LENGTH = 3;
    
    
    /**
     * Class constructor
     */
    public Submarine()
    {
        super(NAME, SYMBOL, LENGTH);
    }
}