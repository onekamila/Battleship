package game;


import GameIO.CoordinateParser;
import GameIO.Input;
import GameIO.Output;
import GameIO.Validators.PlacementValidator;
import game.Ships.Fleet;
import game.Ships.Ship;


/**
 * Represents a single turn for placing every <code>Ship</code> in a <code>Player</code>'s <code>Fleet</code>
 */
public class PlacementTurn
{
    private Player player;
    private PlacementValidator validator;
    private Input input;
    private Output output;
    
    
    /**
     * Class constructor
     *
     * @param player the <code>Player</code> for this turn
     */
    public PlacementTurn(Player player)
    {
        this.player = player;
        init();
    }
    
    
    private void init()
    {
        this.input = Input.getInstance();
        this.output = Output.getInstance();
        validator = new PlacementValidator(player.getBoard());
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
        output.println(player.getBoard().toString());
    
        boolean invalidStart = true;
        boolean invalidEnd = true;
        String coord1Str = "";
        String coord2Str = "";
    
        while(invalidStart)
        {
            // Ask for user input
            output.print("Where would you like to start your " + ship.getName() + "? (Length: " + ship.getLength() + "): ");
            coord1Str = input.readLine();
        
            // Validate first coordinate
            if (validator.validate(coord1Str))
            {
                invalidStart = false;
            }
            else
            {
                output.println("Invalid input! Please try again.");
            }
        }
    
        while(invalidEnd)
        {
            output.print("Where would you like to end your " + ship.getName() + "? (Length: " + ship.getLength() + "): ");
            coord2Str = input.readLine();
        
            // Validate second coordinate
            if (validator.validate(coord1Str, coord2Str, ship))
            {
                invalidEnd = false;
            }
            else
            {
                output.println("Invalid input! Please try again.");
            }
        }
    
        // Parse input
        int[] coord1 = CoordinateParser.parse(coord1Str);
        int[] coord2 = CoordinateParser.parse(coord2Str);
    
    
        player.placeShip(coord1[0], coord1[1], coord2[0], coord2[1], ship);
        output.println("\n");
    }
}