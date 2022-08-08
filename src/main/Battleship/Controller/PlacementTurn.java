package Battleship.Controller;


import Battleship.GameIO.Validators.PlacementValidator;
import Battleship.game.GameBoard.Coordinate;
import Battleship.game.Player;
import Battleship.game.Ships.Fleet;
import Battleship.game.Ships.Ship;


/**
 * Represents a single turn for placing every <code>Ship</code> in a <code>Player</code>'s <code>Fleet</code>
 *
 * @see Player
 * @see PlacementValidator
 *
 * @author Garrett Kamila Crayton
 * @version 0.2.2
 * @since 0.1.0
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
        Coordinate coord1 = new Coordinate(coord1Str);
        Coordinate coord2 = new Coordinate(coord2Str);
        
        player.placeShip(coord1, coord2, ship);
        println("\n");
    }
}