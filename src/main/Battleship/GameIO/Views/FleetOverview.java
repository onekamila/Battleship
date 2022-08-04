package Battleship.GameIO.Views;


import Battleship.game.Game;
import Battleship.game.Ships.Fleet;
import Battleship.game.Ships.Ship;


/**
 * Displays the status of each <code>Player</code>'s <code>Fleet</code>
 *
 * @see Game
 * @see Battleship.game.Player
 * @see Fleet
 *
 * @author Garrett Kamila Crayton
 * @version 0.2.0
 * @since 0.0.0
 */
public class FleetOverview extends View
{
    /**
     * Class constructor
     *
     * @param game the <code>Game</code> this <code>View</code> is based on
     */
    public FleetOverview(Game game)
    {
        super(game);
    }
    
    
    /**
     * Returns a <code>String</code> representation of this <code>View</code>
     *
     * @see Game#getCurrentPlayer()
     * @see Game#getCurrentOpponent()
     *
     * @return a <code>String</code> representation of this <code>View</code>
     */
    @Override
    public String toString()
    {
        updatePlayers();
        
        Fleet fleet1 = player.getFleet();
        Fleet fleet2 = opponent.getFleet();
        
        String outStr = "\t\t\t\t   Remaining Ships\t\t\t\t\n";
        outStr += "====================================================\n";
        outStr += "\t\t My Fleet \t\t\t\t Enemy Fleet\t\t\n";
        outStr += "------------------------\t------------------------\n";
        
        for(int i = 0; i < 9; i++)
        {
            String ship1Str = shipStatus(fleet1.get(i));
            String ship2Str = shipStatus(fleet2.get(i));
            
            outStr += ship1Str + "\t" + ship2Str + "\n";
        }
        
        return outStr;
    }
    
    private static String shipStatus(Ship ship)
    {
        String shipName = ship.getName();
        
        if(ship.sunk())
            shipName += "(S)";
        
        String outStr = String.format("%-20s (%d)", shipName, ship.getHits());
        
        return outStr;
    }
}