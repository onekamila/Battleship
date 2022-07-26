package game;


import GameIO.Validators.MoveValidator;
import game.GameBoard.Square;
import GameIO.*;
import game.Ships.Ship;
import GameIO.Views.PlayerView;


/**
 * Represents a turn for a single <code>Player</code>
 *
 * @author Garrett Kamila Crayton
 * @version 0.0.0
 */
public class Turn
{
    private Player player;
    private Player opponent;
    private PlayerView view;
    private MoveValidator validator;
    private MoveHistory history;
    private Input in;
    private Output out;
    
    
    /**
     * Class constructor
     *
     * @param player the <code>Player</code> making a move during this turn
     * @param opponent the opposing <code>Player</code>
     * @param history the <code>MoveHistory</code> for the game
     */
    public Turn(Player player, Player opponent, MoveHistory history)
    {
        this.player = player;
        this.opponent = opponent;
        this.history = history;
        
        init();
    }
    
    private void init()
    {
        in = Input.getInstance();
        out = Output.getInstance();
        
        validator = new MoveValidator(player.getOpponentBoard());
        
        view = new PlayerView(player, opponent, history);
    }
    
    
    /**
     * Returns the <code>PlayerView</code> for this turn's <code>Player</code>
     *
     * @return the <code>PlayerView</code> for this turn's <code>Player</code>
     */
    public PlayerView getView()
    {
        return view;
    }
    
    
    /**
     * Start the current <code>Turn</code>
     *
     * @return <code>true</code> if the input was valid, <code>false</code> otherwise
     */
    public boolean start()
    {
        // Display player view
        out.println(view.toString());
        
        // Ask for input
        out.print("Enter target coordinate: ");
        String coordStr = in.readLine();
        
        // Validate input
        if(invalid(coordStr))
        {
            out.println("Invalid input! Please try again.");
            //start();
            return false;
        }
        
        // Parse coordinate
        int[] coord = CoordinateParser.parse(coordStr);
    
        // Make move
        Move move = player.move(coord[0], coord[1]);
        opponent.checkFleet();
        
        // Add move to move history
        history.add(move);
        
        // GameIO.Output result
        outputResult(move);
        
        return true;
    }
    
    private boolean invalid(String coordStr)
    {
        return !validator.validate(coordStr);
    }
    
    private void outputResult(Move move)
    {
        Square square = move.getSquare();
        Result result = move.getResult();
        String outStr = result.name() + "!";
        
        // If a hit or sunk, get the correct output
        if(result != Result.MISS)
        {
            // Get the ship that was hit
            Ship hitShip = square.getShip();
            
            // If a hit
            if(result == Result.HIT)
            {
                outStr += " - " + hitShip.getName();
            }
            else
            {
                outStr = "You sank my " + hitShip.getName() + "!";
            }
        }
        
        out.println(outStr);
    }
}