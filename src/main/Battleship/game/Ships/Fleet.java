package Battleship.game.Ships;


import java.util.ArrayList;


/**
 * Represents a full fleet of <code>Ship</code>s
 *
 * @see Ship
 * @see ArrayList
 *
 * @author Garrett Kamila Crayton
 * @version 0.2.1
 * @since 0.0.0
 */
public class Fleet extends ArrayList<Ship>
{
    /**
     * Class constructor
     */
    public Fleet()
    {
        add(new AircraftCarrier());
        add(new Battleship());
        add(new Cruiser());
        add(new Cruiser());
        add(new Submarine());
        add(new Submarine());
        add(new Destroyer());
        add(new Destroyer());
        add(new Destroyer());
    }
    
    
    /**
     * Return the number of hits the fleet has sustained
     *
     * @see Ship
     *
     * @return the total number of hits on every <code>Ship</code> in the fleet
     */
    public int getHits()
    {
        int hits = 0;
        
        for(Ship ship: this)
        {
            hits += ship.getHits();
        }
        
        return hits;
    }
}