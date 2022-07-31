package Battleship.GameIO.Validators;


import Battleship.GameIO.CoordinateParser;
import Battleship.game.GameBoard.Board;


/**
 * Validates the input from the user
 *
 * @see Board
 *
 * @author Garrett Kamila Crayton
 * @version 0.1.0
 * @since 0.0.0
 */
public abstract class InputValidator
{
    /**
     * The <code>Board</code> this <code>Validator</code> will use to validate the given input
     */
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
     * @see Board#checkAvailable(int, int)
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
    
    /**
     * Parse the coordinate from the given <code>String</code>
     *
     * @see CoordinateParser
     *
     * @param coord the coordinate as a <code>String</code>
     * @return the coordinate as an <code>int[]</code>
     */
    protected int[] parse(String coord)
    {
        return CoordinateParser.parse(coord);
    }
}