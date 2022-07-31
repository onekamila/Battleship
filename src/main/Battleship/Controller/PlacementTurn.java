package Battleship.Controller;


import Battleship.GameIO.Validators.PlacementValidator;
import Battleship.game.Player;
import Battleship.game.Ships.Fleet;
import Battleship.game.Ships.Ship;


/**
 * Represents a single turn for placing every <code>Ship</code> in a <code>Player</code>'s <code>Fleet</code>
 *
 * @author Garrett Kamila Crayton
 * @version 0.0.0
 */
public class PlacementTurn extends TurnController
{
    private Player player;
    private PlacementValidator validator;
    
    
    /**
     * Class constructor
     *
     * @param player the <code>Player</code> for this turn
     */
    public PlacementTurn(Player player)
    {
        super(new PlacementValidator(player.getBoard()));
        this.player = player;
        this.validator = (PlacementValidator) super.validator;
    }
    
    
    /**
     * Run the full <code>PlacementTurn</code>
     */
    public void start()
    {
        Fleet fleet = player.getFleet();
    
        // Loop through fleet
        for(Ship ship: fleet)
        {
            placeShip(ship);
        }
    }
    
    /**
     * Place a single <code>Ship</code>
     *
     * @param ship the <code>Ship</code> to be placed
     */
    public void placeShip(Ship ship)
    {
        println(player.getBoard().toString());
    
        boolean invalidStart = true;
        boolean invalidEnd = true;
        String coord1Str = "";
        String coord2Str = "";
    
        while(invalidStart)
        {
            // Ask for user input
            print("Where would you like to start your " + ship.getName() + "? (Length: " + ship.getLength() + "): ");
            coord1Str = readLine();
        
            // Validate first coordinate
            if (validator.validate(coord1Str))
            {
                invalidStart = false;
            }
            else
            {
                println("Invalid input! Please try again.");
            }
        }
    
        while(invalidEnd)
        {
            print("Where would you like to end your " + ship.getName() + "? (Length: " + ship.getLength() + "): ");
            coord2Str = readLine();
        
            // Validate second coordinate
            if (validator.validate(coord1Str, coord2Str, ship))
            {
                invalidEnd = false;
            }
            else
            {
                println("Invalid input! Please try again.");
            }
        }
    
        // Parse input
        int[] coord1 = parseCoord(coord1Str);
        int[] coord2 = parseCoord(coord2Str);
    
    
        player.placeShip(coord1[0], coord1[1], coord2[0], coord2[1], ship);
        println("\n");
    }
}