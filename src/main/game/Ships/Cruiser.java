package game.Ships;


/**
 * Represents a Cruiser
 */
public class Cruiser extends Ship
{
    private static final String NAME = "Cruiser";
    private static final char SYMBOL = 'C';
    private static final int LENGTH = 3;
    
    
    /**
     * Class constructor
     */
    public Cruiser()
    {
        super(NAME, SYMBOL, LENGTH);
    }
}