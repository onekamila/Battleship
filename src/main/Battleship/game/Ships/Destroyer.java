package Battleship.game.Ships;


/**
 * Represents a Destroyer
 *
 * @author Garrett Kamila Crayton
 * @version 0.0.0
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