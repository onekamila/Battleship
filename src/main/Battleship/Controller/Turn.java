package Battleship.Controller;


import Battleship.GameIO.Validators.MoveValidator;
import Battleship.game.*;
import Battleship.game.GameBoard.Coordinate;


/**
 * Represents a single turn
 *
 * @see Game
 *
 * @author Garrett Kamila Crayton
 * @version 0.2.2
 * @since 0.1.0
 */
public class Turn extends TurnController
{
    private Game game;
    
    
    /**
     * Class constructor
     * @param game the <code>Game</code> this <code>Turn</code> is played for
     */
    public Turn(Game game)
    {
        super(new MoveValidator(game));
        this.game = game;
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
        if(!validate(coordStr))
        {
            println("Invalid input! Please try again.");
            return false;
        }
        
        // Parse coordinate
        Coordinate coord = new Coordinate(coordStr);
        
        // Make move
        game.move(coord);
        
        return true;
    }
}