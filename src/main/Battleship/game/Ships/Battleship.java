package Battleship.game.Ships;


/**
 * Represents a Battleship
 *
 * @see Ship
 *
 * @author Garrett Kamila Crayton
 * @version 0.2.0
 * @since 0.0.0
 */
public class Battleship extends Ship
{
    private static final String NAME = "Battleship";
    private static final char SYMBOL = 'B';
    private static final int LENGTH = 4;
    
    
    /**
     * Class constructor
     *
     * @see Ship
     */
    public Battleship()
    {
        super(NAME, SYMBOL, LENGTH);
    }
}