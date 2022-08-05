package Battleship.game.Ships;


/**
 * Represents a single ship
 *
 * @author Garrett Kamila Crayton
 * @version 0.2.1
 * @since 0.0.0
 */
public abstract class Ship
{
    private String name;
    private char symbol;
    private int length;
    private int hits;
    
    
    /**
     * Class constructor
     *
     * @param name the name of the <code>Ship</code>
     * @param symbol the character displayed for the <code>Ship</code>
     * @param length the number of <code>Square</code>s the <code>Ship</code> occupies
     */
    protected Ship(String name, char symbol, int length)
    {
        this.name = name;
        this.symbol = symbol;
        this.length = length;
        this.hits = 0;
    }
    
    
    /**
     * Returns the name of the <code>Ship</code>
     *
     * @return the name of the <code>Ship</code>
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Return the length of the <code>Ship</code>
     *
     * @return the length of the <code>Ship</code>
     */
    public int getLength()
    {
        return length;
    }
    
    /**
     * Return the number of hits the <code>Ship</code> has sustained
     *
     * @return the number of hits landed on the <code>Ship</code>
     */
    public int getHits()
    {
        return hits;
    }
    
    
    /**
     * Simulates hitting the <code>Ship</code>
     */
    public void hit()
    {
        this.hits++;
    }
    
    /**
     * Return whether or not the <code>Ship</code> has been sunk
     *
     * @return <code>true</code> if the number of hits is equal to the length of the <code>Ship</code>,
     *          <code>false</code> otherwise
     */
    public boolean sunk()
    {
        return (hits == length);
    }
    
    
    /**
     * Returns a <code>String</code> representation of this of the <code>Ship</code>
     *
     * @return the character code for the <code>Ship</code>
     */
    @Override
    public String toString()
    {
        return Character.toString(symbol);
    }
}