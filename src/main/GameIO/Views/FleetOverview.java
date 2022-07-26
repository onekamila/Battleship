package GameIO.Views;


import game.Player;
import game.Ships.Fleet;
import game.Ships.Ship;


/**
 * Displays the status of each <code>Player</code>'s <code>Fleet</code>
 *
 * @author Garrett Kamila Crayton
 * @version 0.0.0
 */
public class FleetOverview
{
    private Player player1;
    private Player player2;
    
    
    /**
     * Class constructor
     * @param player1 the current <code>Player</code>
     * @param player2 the current <code>Opponent</code>
     */
    public FleetOverview(Player player1, Player player2)
    {
        this.player1 = player1;
        this.player2 = player2;
    }
    
    
    /**
     * Returns a <code>String</code> representation of this view
     *
     * @return a <code>String</code> representation of this view
     */
    public String toString()
    {
        String outStr = "\t\t\t\t   Remaining Ships\t\t\t\t\n";
        outStr += "====================================================\n";
        outStr += "\t\t My Fleet \t\t\t\t Enemy Fleet\t\t\n";
        outStr += "------------------------\t------------------------\n";
        
        Fleet fleet1 = player1.getFleet();
        Fleet fleet2 = player2.getFleet();
        
        for(int i = 0; i < 9; i++)
        {
            String ship1Str = shipStatus(fleet1.get(i));
            String ship2Str = shipStatus(fleet2.get(i));
            
            outStr += ship1Str + "\t" + ship2Str + "\n";
        }
        
        return outStr;
    }
    
    
    /**
     * Returns the status of the specified <code>Ship</code>
     *
     * @param ship the <code>Ship</code> to get the status from
     * @return the status of the given <code>Ship</code>
     */
    public static String shipStatus(Ship ship)
    {
        String shipName = ship.getName();
        
        if(ship.sunk())
            shipName += "(S)";
        
        String outStr = String.format("%-20s (%d)", shipName, ship.getHits());
        
        return outStr;
    }
}