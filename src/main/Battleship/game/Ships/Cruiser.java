package Battleship.game.Ships;


/**
 * Represents a Cruiser
 *
 * @see Ship
 *
 * @author Garrett Kamila Crayton
 * @version 0.1.0
 * @since 0.0.0
 */
public class Cruiser extends Ship
{
    private static final String NAME = "Cruiser";
    private static final char SYMBOL = 'C';
    private static final int LENGTH = 3;
    
    
    /**
     * Class constructor
     *
     * @see Ship
     */
    public Cruiser()
    {
        super(NAME, SYMBOL, LENGTH);
    }
}