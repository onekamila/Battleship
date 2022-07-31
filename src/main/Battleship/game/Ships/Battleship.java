package Battleship.game.Ships;


/**
 * Represents a Battleship
 *
 * @author Garrett Kamila Crayton
 * @version 0.0.0
 */
public class Battleship extends Ship
{
    private static final String NAME = "Battleship";
    private static final char SYMBOL = 'B';
    private static final int LENGTH = 4;
    
    
    /**
     * Class constructor
     */
    public Battleship()
    {
        super(NAME, SYMBOL, LENGTH);
    }
}