package game.Ships;


/**
 * Represents a Destroyer
 */
public class Destroyer extends Ship
{
    private static final String NAME = "Destroyer";
    private static final char SYMBOL = 'D';
    private static final int LENGTH = 2;
    
    
    /**
     * Class constructor
     */
    public Destroyer()
    {
        super(NAME, SYMBOL, LENGTH);
    }
}