package GameIO.Views;


import game.Player;
import game.Ships.Ship;

import java.util.ArrayList;


public class FleetOverview
{
    //private Fleet fleet1;
    //private Fleet fleet2;
    private Player player1;
    private Player player2;
    
    public FleetOverview(Player player1, Player player2)
    {
        //fleet1 = player1.getFleet();
        //fleet2 = player2.getFleet();
        this.player1 = player1;
        this.player2 = player2;
    }
    
    public String toString()
    {
        String outStr = "\t\t\t\t   Remaining Ships\t\t\t\t\n";
        outStr += "====================================================\n";
        outStr += "\t\t My Fleet \t\t\t\t Enemy Fleet\t\t\n";
        outStr += "------------------------\t------------------------\n";
        
        ArrayList<Ship> fleet1 = player1.getFleet();
        ArrayList<Ship> p2Fleet = player2.getFleet();
        
        for(int i = 0; i < 9; i++)
        {
            String ship1Str = shipStatus(fleet1.get(i));
            String ship2Str = shipStatus(p2Fleet.get(i));
            
            outStr += ship1Str + "\t" + ship2Str + "\n";
        }
        
        return outStr;
    }
    
    
    public static String shipStatus(Ship ship)
    {
        String shipName = ship.getName();
        
        if(ship.sunk())
            shipName += "(S)";
        
        String outStr = String.format("%-20s (%d)", shipName, ship.getHits());
        
        return outStr;
    }
}
