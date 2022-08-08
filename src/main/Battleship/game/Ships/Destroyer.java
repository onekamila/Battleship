package Battleship.game.Ships;


/**
 * Represents a Destroyer
 *
 * @see Ship
 *
 * @author Garrett Kamila Crayton
 * @version 0.2.2
 * @since 0.0.0
 */
public class Destroyer extends Ship
{
    private static final String NAME = "Destroyer";
    private static final char SYMBOL = 'D';
    private static final int LENGTH = 2;
    
    
    /**
     * Class constructor
     *
     * @see Ship
     */
    public Destroyer()
    {
        super(NAME, SYMBOL, LENGTH);
    }
}