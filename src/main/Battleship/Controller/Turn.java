package Battleship.Controller;


import Battleship.GameIO.Validators.MoveValidator;
import Battleship.game.*;


/**
 * Represents a single turn
 *
 * @author Garrett Kamila Crayton
 * @version 0.0.0
 */
public class Turn extends TurnController
{
    private Game game;
    //private MoveValidator validator;
    
    
    /**
     * Class constructor
     * @param game the <code>Game</code> this <code>Turn</code> is played for
     */
    public Turn(Game game)
    {
        super(new MoveValidator(game));
        this.game = game;
        //validator = new MoveValidator(Battleship.game);
    }
    
    
    /**
     * Start the current <code>Turn</code>
     *
     * @return <code>true</code> if the input was valid, <code>false</code> otherwise
     */
    public boolean start()
    {
        // Ask for input
        print("Enter target coordinate: ");
        String coordStr = readLine();
        
        // Validate input
        //if(invalid(coordStr))
        if(!validate(coordStr))
        {
            println("Invalid input! Please try again.");
            return false;
        }
        
        // Parse coordinate
        int[] coord = parseCoord(coordStr);
    
        // Make move
        game.move(coord[0], coord[1]);
        
        return true;
    }
    
    /*private boolean invalid(String coordStr)
    {
        return !validator.validate(coordStr);
    }*/
}