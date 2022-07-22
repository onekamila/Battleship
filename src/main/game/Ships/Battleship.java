package game.Ships;


/**
 * Represents a Battleship
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