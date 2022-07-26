package game.Ships;


/**
 * Represents a Cruiser
 *
 * @author Garrett Kamila Crayton
 * @version 0.0.0
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