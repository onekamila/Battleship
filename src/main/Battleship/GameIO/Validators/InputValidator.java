package Battleship.GameIO.Validators;


import Battleship.GameIO.CoordinateParser;
import Battleship.game.GameBoard.Board;


/**
 * Validates the input from the user
 *
 * @author Garrett Kamila Crayton
 * @version 0.0.0
 */
public abstract class InputValidator
{
    protected Board board;
    
    
    /**
     * Class constructor
     *
     * @param board the <code>Board</code> that the input will be applied to
     */
    public InputValidator(Board board)
    {
        this.board = board;
    }
    
    
    /**
     * Validate the input given
     *
     * @param input the input to be validated
     * @return <code>true</code> if the input given is valid, <code>false</code> otherwise
     */
    public boolean validate(String input)
    {
        try
        {
            int[] coord = parse(input);
        
            if ((coord[0] < 10) && (coord[1] < 10))
            {
                return board.checkAvailable(coord[0], coord[1]);
            }
            
            return false;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
    protected int[] parse(String coord)
    {
        return CoordinateParser.parse(coord);
    }
}