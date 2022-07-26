package game.Ships;


/**
 * Represents an Aircraft Carrier
 *
 * @author Garrett Kamila Crayton
 * @version 0.0.0
 */
public class AircraftCarrier extends Ship
{
    private static final String NAME = "Aircraft Carrier";
    private static final char SYMBOL = 'A';
    private static final int LENGTH = 5;
    
    
    /**
     * Class constructor
     */
    public AircraftCarrier()
    {
        super(NAME, SYMBOL, LENGTH);
    }
}